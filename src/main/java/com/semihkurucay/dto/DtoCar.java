package com.semihkurucay.dto;

import java.math.BigDecimal;

import com.semihkurucay.enums.CarStatusType;
import com.semihkurucay.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends DtoBaseEntity {

	private String plaka;
	private String brand;
	private String model;
	private Integer productionYear;
	private Long km;
	private BigDecimal price;
	private BigDecimal damegeAmount;
	private CurrencyType currencyType;
	private CarStatusType carStatusType;
}
