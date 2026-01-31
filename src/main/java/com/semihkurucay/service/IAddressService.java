package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.entity.Address;

public interface IAddressService {

	public Page<DtoAddress> findAllPageable(Pageable pageable);
	public DtoAddress findById(Long addressId);
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	public DtoAddress updateAddress(Long addressId, DtoAddressIU dtoAddressIU);
	public boolean deleteAddress(Long addressId);
}
