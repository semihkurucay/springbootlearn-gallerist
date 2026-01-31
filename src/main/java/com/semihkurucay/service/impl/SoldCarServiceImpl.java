package com.semihkurucay.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semihkurucay.dto.CurrencyRatesResponse;
import com.semihkurucay.dto.DtoSoldCar;
import com.semihkurucay.dto.DtoSoldCarIU;
import com.semihkurucay.entity.Car;
import com.semihkurucay.entity.Customer;
import com.semihkurucay.entity.Gallerist;
import com.semihkurucay.entity.SoldCar;
import com.semihkurucay.enums.CarStatusType;
import com.semihkurucay.enums.CurrencyType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.ISoldCarMapper;
import com.semihkurucay.repository.ICarRepository;
import com.semihkurucay.repository.ICustomerRepository;
import com.semihkurucay.repository.IGalleristRepository;
import com.semihkurucay.repository.ISoldCarRepository;
import com.semihkurucay.service.ICurrencyRateService;
import com.semihkurucay.service.ISoldCarService;
import com.semihkurucay.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoldCarServiceImpl implements ISoldCarService {

	private final ISoldCarRepository soldCarRepository;

	private final ICustomerRepository customerRepository;

	private final ICarRepository carRepository;

	private final IGalleristRepository galleristRepository;

	private final ISoldCarMapper soldCarMapper;

	private final ICurrencyRateService currencyRateService;

	private boolean checkCarStatus(Car car) {
		return car.getCarStatusType() != CarStatusType.SALED;
	}

	private boolean checkCurrencyType(Customer customer, Car car) {
		return car.getCurrencyType() == customer.getAccount().getCurrencyType();
	}

	private boolean checkAmount(Customer customer, Car car, BigDecimal dailyUsdExchange) {
		return remainingAmount(customer, car, dailyUsdExchange).compareTo(BigDecimal.ZERO) >= 0;
	}

	private BigDecimal getUsdExchange() {
		CurrencyRatesResponse currencyRatesResponse = currencyRateService
				.getCurrencyRatesResponse(DateUtil.getCurrentDate(new Date()), DateUtil.getCurrentDate(new Date()));
		return new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
	}

	private BigDecimal convertTLToUSD(BigDecimal money, BigDecimal dailyUsdExchange) {
		return money.divide(dailyUsdExchange, 2, RoundingMode.HALF_UP);
	}

	private BigDecimal convertUSDToTL(BigDecimal money, BigDecimal dailyUsdExchange) {
		return money.multiply(dailyUsdExchange);
	}

	private BigDecimal remainingAmount(Customer customer, Car car, BigDecimal dailyUsdExchange) {
		BigDecimal customerAmount = customer.getAccount().getAmount();
		BigDecimal carPrice = car.getPrice();

		if (!checkCurrencyType(customer, car)) {
			if (car.getCurrencyType() != CurrencyType.USD) {
				carPrice = convertTLToUSD(carPrice, dailyUsdExchange);
			} else {
				customerAmount = convertTLToUSD(customerAmount, dailyUsdExchange);
			}
		}

		return customerAmount.subtract(carPrice);
	}

	private BigDecimal remainingCustomerAmount(Customer customer, Car car, BigDecimal dailyUsdExchange) {
		BigDecimal customerAmount = remainingAmount(customer, car, dailyUsdExchange);

		if (!checkCurrencyType(customer, car)) {
			if (customer.getAccount().getCurrencyType() != CurrencyType.USD) {
				return convertUSDToTL(customerAmount, dailyUsdExchange);
			}
		}

		return customerAmount;
	}

	private SoldCar createSeladCar(Car car, Gallerist gallerist, Customer customer) {

		SoldCar seladCar = new SoldCar();
		seladCar.setCreateTime(new Date());

		seladCar.setCar(car);
		seladCar.setCustomer(customer);
		seladCar.setGallerist(gallerist);

		return seladCar;
	}

	@Transactional// sqlde hata çıkarsa rollback işlemi yapacak
	@Override
	public DtoSoldCar buyCar(DtoSoldCarIU dtoSeladCarIU) {
		Car car = carRepository.findById(dtoSeladCarIU.getCarId()).orElseThrow(() -> new BaseException(
				new ErrorMessage(dtoSeladCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST)));

		Customer customer = customerRepository.findById(dtoSeladCarIU.getCustomerId())
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(dtoSeladCarIU.getCustomerId().toString(), MessageType.NO_RECORD_EXIST)));

		Gallerist gallerist = galleristRepository.findById(dtoSeladCarIU.getGalleristId())
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(dtoSeladCarIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST)));

		if (!checkCarStatus(car)) {
			throw new BaseException(
					new ErrorMessage(car.getCarStatusType().name(), MessageType.CAR_STATUS_IS_ALREAD_SALED));
		}

		BigDecimal dailyUsdExchange = getUsdExchange();
		
		if (!checkAmount(customer, car, dailyUsdExchange)) {
			throw new BaseException(new ErrorMessage(null, MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH));
		}

		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);

		customer.getAccount().setAmount(remainingCustomerAmount(customer, car, dailyUsdExchange));
		customerRepository.save(customer);

		return soldCarMapper.toDto(soldCarRepository.save(createSeladCar(car, gallerist, customer)));
	}

}
