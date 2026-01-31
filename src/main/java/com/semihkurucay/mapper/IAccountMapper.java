package com.semihkurucay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.semihkurucay.dto.DtoAccount;
import com.semihkurucay.dto.DtoAccountIU;
import com.semihkurucay.entity.Account;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

	public DtoAccount toDto(Account account);
	public Account toAccount(DtoAccountIU dtoAccountIU);
	public Account updateAccount(DtoAccountIU dtoAccountIU, @MappingTarget Account account);
}
