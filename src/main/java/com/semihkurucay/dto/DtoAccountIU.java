package com.semihkurucay.dto;

import java.math.BigDecimal;

import com.semihkurucay.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU {

	@NotEmpty(message = "Account no boş geçilemez")
	private String accountNo;
	
	@NotEmpty(message = "Iban boş geçilemez")
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}
