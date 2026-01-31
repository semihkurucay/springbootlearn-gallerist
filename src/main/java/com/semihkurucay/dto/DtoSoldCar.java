package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCar extends DtoBaseEntity {

	private DtoGalleristCar galleristCar;
	
	private DtoCar car;
	
	private DtoCustomer customer;
}
