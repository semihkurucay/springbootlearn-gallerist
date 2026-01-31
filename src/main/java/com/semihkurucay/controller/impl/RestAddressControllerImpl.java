package com.semihkurucay.controller.impl;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestAddressController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.service.IAddressService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/address")
@RequiredArgsConstructor
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

	private final IAddressService addressService;

	@GetMapping("/list") // http://localhost:8080/rest/api/address/list?pageNumber=0&pageSize=5
	@Override
	public RootEntity<RestPageableEntity<DtoAddress>> findAllPageable(RestPageableRequest pageableRequest) {
		Page<DtoAddress> page = addressService.findAllPageable(toPageable(pageableRequest));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoAddress> findById(@PathVariable(value = "id") Long addressId) {
		return ok(addressService.findById(addressId));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAddress> updateAddress(@PathVariable(value = "id") Long addressId, @Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.updateAddress(addressId, dtoAddressIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteAddress(@PathVariable(value = "id") Long addressId) {
		return ok(addressService.deleteAddress(addressId));
	}
}
