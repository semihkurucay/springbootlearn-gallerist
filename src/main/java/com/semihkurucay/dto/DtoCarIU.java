package com.semihkurucay.dto;

import java.math.BigDecimal;

import com.semihkurucay.enums.CarStatusType;
import com.semihkurucay.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCarIU {

	@NotEmpty(message = "Plaka bilgisi boş geçilemez")
	private String plaka;
	
	@NotEmpty(message = "Araç markası boş geçilemez")
	private String brand;
	
	@NotEmpty(message = "Araç modeli boş geçilemez")
	private String model;
	
	@NotNull(message = "Araç yılı boş geçilemez")
	private Integer productionYear;
	
	@NotNull(message = "Araç km boş geçilemez")
	private Long km;
	
	@NotNull(message = "Fiyat bilgisi boş geçilemez")
	private BigDecimal price;
	
	@NotNull(message = "Hasar fiyat bilgisi boş geçilemez")
	private BigDecimal damegeAmount;
	
	@NotNull(message = "Döviz bilgisi boş geçilemez")
	private CurrencyType currencyType;
	
	@NotNull(message = "Araba durum bilgisi boş geçilemez")
	private CarStatusType carStatusType;
}
