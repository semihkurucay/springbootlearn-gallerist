package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semihkurucay.dto.DtoCustomer;
import com.semihkurucay.dto.DtoCustomerIU;
import com.semihkurucay.entity.Account;
import com.semihkurucay.entity.Address;
import com.semihkurucay.entity.Customer;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.ICustomerMapper;
import com.semihkurucay.repository.IAccountRepository;
import com.semihkurucay.repository.IAddressRepository;
import com.semihkurucay.repository.ICustomerRepository;
import com.semihkurucay.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private final ICustomerRepository customerRepository;
	
	private final IAddressRepository addressRepository;
	
	private final IAccountRepository accountRepository;
	
	private final ICustomerMapper customerMapper;

	@Override
	public Page<DtoCustomer> findAllPageable(Pageable pageable) {
		return customerRepository.findAll(pageable).map(customerMapper::toDto);
	}

	private Customer getCustomerOrThrow(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoCustomer findById(Long id) {
		return customerMapper.toDto(getCustomerOrThrow(id));
	}

	@Transactional
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU customerIU) {
		Address address = addressRepository.findById(customerIU.getAddressId())
				.orElseThrow(() -> new BaseException(new ErrorMessage(customerIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST)));
		
		Account account = accountRepository.findById(customerIU.getAccountId())
				.orElseThrow(() -> new BaseException(new ErrorMessage(customerIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST)));
		
		Customer customer = customerMapper.toCustomer(customerIU);
		customer.setCreateTime(new Date());
		customer.setAccount(account);
		customer.setAddress(address);
		
		return customerMapper.toDto(customerRepository.save(customer));
	}

	@Override
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU customerIU) {
		Customer customer = customerMapper.updateCustomer(customerIU, getCustomerOrThrow(id));
		
		return customerMapper.toDto(customerRepository.save(customer));
	}

	@Override
	public boolean deleteCustomer(Long id) {
		customerRepository.delete(getCustomerOrThrow(id));
		return true;
	}
}
