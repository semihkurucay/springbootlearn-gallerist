package com.semihkurucay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.entity.Address;

@Mapper(componentModel = "spring")
public interface IAddressMapper {

	public DtoAddress toDto(Address address);
	public Address toAddress(DtoAddressIU dtoAddressIU);
	public Address updateAddress(DtoAddressIU dtoAddressIU, @MappingTarget Address address);
}
