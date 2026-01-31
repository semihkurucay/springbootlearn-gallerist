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

import com.semihkurucay.controller.IRestAccountController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoAccount;
import com.semihkurucay.dto.DtoAccountIU;
import com.semihkurucay.service.IAccountService;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/account")
@RequiredArgsConstructor
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {
	
	private final IAccountService accountService;
	
	@GetMapping("/list")
	@Override
	public RootEntity<RestPageableEntity<DtoAccount>> findAllPAgeable(RestPageableRequest pageableRequest) {
		Page<DtoAccount> page = accountService.findAllPAgeable(toPageable(pageableRequest));
		return ok(toPageableResponse(page, page.getContent()));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoAccount> findById(@PathVariable(value = "id") Long id) {
		return ok(accountService.findById(id));
	}

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable(value = "id") Long accountId, @Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.updateAccount(accountId, dtoAccountIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<Boolean> deleteAccount(@PathVariable(value = "id") Long accountId) {
		return ok(accountService.deleteAccount(accountId));
	}

}
