package com.semihkurucay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semihkurucay.dto.DtoAccount;
import com.semihkurucay.dto.DtoAccountIU;
import com.semihkurucay.entity.Account;

public interface IAccountService {

	public Page<DtoAccount> findAllPAgeable(Pageable pageable);
	public DtoAccount findById(Long id);
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	public DtoAccount updateAccount(Long accountId, DtoAccountIU dtoAccountIU);
	public boolean deleteAccount(Long accountId);
}
