package com.semihkurucay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.semihkurucay.dto.DtoCar;
import com.semihkurucay.dto.DtoCarIU;
import com.semihkurucay.entity.Car;

@Mapper(componentModel = "spring")
public interface ICarMapper {

	public DtoCar toDto(Car car);
	public Car toCar(DtoCarIU dtoCarIU);
	public Car updateCar(DtoCarIU dtoCarIU, @MappingTarget Car car);
}
