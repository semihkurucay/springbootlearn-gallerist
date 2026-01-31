package com.semihkurucay.mapper;

import org.mapstruct.Mapper;

import com.semihkurucay.dto.DtoGalleristCar;
import com.semihkurucay.entity.GalleristCar;

@Mapper(componentModel = "spring", uses = {IGalleristMapper.class, ICarMapper.class})
public interface IGalleristCarMapper {

	public DtoGalleristCar toDto(GalleristCar galleristCar);
}
