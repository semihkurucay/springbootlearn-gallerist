package com.semihkurucay.dto;

import java.math.BigDecimal;

import com.semihkurucay.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount extends DtoBaseEntity {
	
	private String accountNo;
	private String iban;
	private BigDecimal amount;
	private CurrencyType currencyType;
}
