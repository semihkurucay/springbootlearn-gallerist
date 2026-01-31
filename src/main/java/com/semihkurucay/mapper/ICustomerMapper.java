package com.semihkurucay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.semihkurucay.dto.DtoCustomer;
import com.semihkurucay.dto.DtoCustomerIU;
import com.semihkurucay.entity.Customer;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IAccountMapper.class})
public interface ICustomerMapper {

	public DtoCustomer toDto(Customer customer);
	public Customer toCustomer(DtoCustomerIU dtoCustomerIU);
	public Customer updateCustomer(DtoCustomerIU dtoCustomerIU, @MappingTarget Customer customer);
}
