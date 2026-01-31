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

import com.semihkurucay.controller.IRestCustomerController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoCustomer;
import com.semihkurucay.dto.DtoCustomerIU;
import com.semihkurucay.service.ICustomerService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/customer")
@RequiredArgsConstructor
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
	
	private final ICustomerService customerService;
	
	@GetMapping("/list")
	@Override
	public RootEntity<RestPageableEntity<DtoCustomer>> findAllPageable(RestPageableRequest pageableRequest) {
		Page<DtoCustomer> page = customerService.findAllPageable(toPageable(pageableRequest));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoCustomer> findById(@PathVariable(value = "id") Long id) {
		return ok(customerService.findById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU customerIU) {
		return ok(customerService.saveCustomer(customerIU));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@PathVariable(value = "id") Long id, @Valid @RequestBody DtoCustomerIU customerIU) {
		return ok(customerService.updateCustomer(id, customerIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteCustomer(@PathVariable(value = "id") Long id) {
		return ok(customerService.deleteCustomer(id));
	}

}
