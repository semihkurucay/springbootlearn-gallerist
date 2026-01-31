package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestAddressController {

	public RootEntity<RestPageableEntity<DtoAddress>> findAllPageable(RestPageableRequest pageableRequest);
	public RootEntity<DtoAddress> findById(Long addressId);
	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	public RootEntity<DtoAddress> updateAddress(Long addressId, DtoAddressIU dtoAddressIU);
	public RootEntity<Boolean> deleteAddress(Long addressId);
}
