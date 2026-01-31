package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.entity.Address;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.IAddressMapper;
import com.semihkurucay.repository.IAddressRepository;
import com.semihkurucay.service.IAddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

	private final IAddressRepository addressRepository;
	
	private final IAddressMapper addressMapper;

	@Override
	public Page<DtoAddress> findAllPageable(Pageable pageable) {
		return addressRepository.findAll(pageable).map(addressMapper::toDto);
	}

	private Address getAddressOrThrow(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoAddress findById(Long addressId) {
		return addressMapper.toDto(getAddressOrThrow(addressId));
	}

	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		Address address = addressMapper.toAddress(dtoAddressIU);
		address.setCreateTime(new Date());
		
		return addressMapper.toDto(addressRepository.save(address));
	}

	@Override
	public DtoAddress updateAddress(Long addressId, DtoAddressIU dtoAddressIU) {
		Address address = addressMapper.updateAddress(dtoAddressIU, getAddressOrThrow(addressId));
		
		return addressMapper.toDto(addressRepository.save(address));
	}

	@Override
	public boolean deleteAddress(Long addressId) {
		addressRepository.delete(getAddressOrThrow(addressId));
		return true;
	}
}
