package com.semihkurucay.service.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.semihkurucay.dto.DtoAccount;
import com.semihkurucay.dto.DtoAccountIU;
import com.semihkurucay.entity.Account;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.mapper.IAccountMapper;
import com.semihkurucay.repository.IAccountRepository;
import com.semihkurucay.service.IAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

	private final IAccountRepository accountRepository;

	private final IAccountMapper accountMapper;
	
	@Override
	public Page<DtoAccount> findAllPAgeable(Pageable pageable) {
		return accountRepository.findAll(pageable).map(accountMapper::toDto);
	}

	private Account getAccountOrThrow(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST)));
	}
	
	@Override
	public DtoAccount findById(Long id) {

		return accountMapper.toDto(getAccountOrThrow(id));
	}

	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		Account account = accountMapper.toAccount(dtoAccountIU);
		account.setCreateTime(new Date());

		return accountMapper.toDto(accountRepository.save(account));
	}

	@Override
	public DtoAccount updateAccount(Long accountId, DtoAccountIU dtoAccountIU) {

		Account account = accountMapper.updateAccount(dtoAccountIU, getAccountOrThrow(accountId));

		return accountMapper.toDto(accountRepository.save(account));
	}

	@Override
	public boolean deleteAccount(Long accountId) {

		accountRepository.delete(getAccountOrThrow(accountId));
		return true;
	}

}
