package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.semihkurucay.dto.DtoCar;
import com.semihkurucay.dto.DtoCarIU;
import com.semihkurucay.entity.Car;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.ICarMapper;
import com.semihkurucay.repository.ICarRepository;
import com.semihkurucay.service.ICarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements ICarService {

	private final ICarRepository carRepository;

	private final ICarMapper carMapper;

	@Override
	public Page<DtoCar> findAllPageable(Pageable pageable) {
		return carRepository.findAll(pageable).map(carMapper::toDto);
	}

	private Car getCarOrThrow(Long id) {
		return carRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoCar findById(Long carId) {
		return carMapper.toDto(getCarOrThrow(carId));
	}

	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car car = carMapper.toCar(dtoCarIU);
		car.setCreateTime(new Date());

		return carMapper.toDto(carRepository.save(car));
	}

	@Override
	public DtoCar updateCar(Long carId, DtoCarIU dtoCarIU) {
		Car car = carMapper.updateCar(dtoCarIU, getCarOrThrow(carId));
		
		return carMapper.toDto(carRepository.save(car));
	}

	@Override
	public boolean deleteCar(Long carId) {
		carRepository.delete(getCarOrThrow(carId));
		return true;
	}

}
