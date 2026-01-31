package com.semihkurucay.mapper;

import org.mapstruct.Mapper;

import com.semihkurucay.dto.DtoSoldCar;
import com.semihkurucay.entity.SoldCar;

@Mapper(componentModel = "spring", uses = {IGalleristMapper.class, ICarMapper.class, ICustomerMapper.class})
public interface ISoldCarMapper {

	public DtoSoldCar toDto(SoldCar seladCar);
}
