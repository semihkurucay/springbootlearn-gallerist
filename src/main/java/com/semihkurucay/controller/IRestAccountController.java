package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoAccount;
import com.semihkurucay.dto.DtoAccountIU;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;

public interface IRestAccountController {

	public RootEntity<RestPageableEntity<DtoAccount>> findAllPAgeable(RestPageableRequest pageableRequest);
	public RootEntity<DtoAccount> findById(Long id);
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
	public RootEntity<DtoAccount> updateAccount(Long accountId, DtoAccountIU dtoAccountIU);
	public RootEntity<Boolean> deleteAccount(Long accountId);
}
