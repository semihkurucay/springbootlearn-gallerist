package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoCustomer;
import com.semihkurucay.dto.DtoCustomerIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestCustomerController {

	public RootEntity<RestPageableEntity<DtoCustomer>> findAllPageable(RestPageableRequest pageableRequest);
	public RootEntity<DtoCustomer> findById(Long id);
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU customerIU);
	public RootEntity<DtoCustomer> updateCustomer(Long id, DtoCustomerIU customerIU);
	public RootEntity<Boolean> deleteCustomer(Long id);
}
