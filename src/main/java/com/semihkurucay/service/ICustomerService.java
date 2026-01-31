package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoCustomer;
import com.semihkurucay.dto.DtoCustomerIU;

public interface ICustomerService {

	public Page<DtoCustomer> findAllPageable(Pageable pageable);
	public DtoCustomer findById(Long id);
	public DtoCustomer saveCustomer(DtoCustomerIU customerIU);
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU customerIU);
	public boolean deleteCustomer(Long id);
}
