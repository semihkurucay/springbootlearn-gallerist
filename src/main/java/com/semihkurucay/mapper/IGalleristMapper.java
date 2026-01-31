package com.semihkurucay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.semihkurucay.dto.DtoGallerist;
import com.semihkurucay.dto.DtoGalleristIU;
import com.semihkurucay.entity.Gallerist;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class})
public interface IGalleristMapper {

	public DtoGallerist toDto(Gallerist gallerist);
	public Gallerist toGallerist(DtoGalleristIU dtoGalleristIU);
	public Gallerist updateGallerist(DtoGalleristIU dtoGalleristIU, @MappingTarget Gallerist gallerist);
}
