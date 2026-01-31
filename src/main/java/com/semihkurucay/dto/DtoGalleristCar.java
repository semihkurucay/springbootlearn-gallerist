package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends DtoBaseEntity {

	private DtoGallerist gallerist;
	private DtoCar car;
}
