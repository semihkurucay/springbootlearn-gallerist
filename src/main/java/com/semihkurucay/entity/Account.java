package com.semihkurucay.entity;

import java.math.BigDecimal;

import com.semihkurucay.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "iban")
	private String iban;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "currency_type")	//EnumType.STRING -> enamın değerini yazar
	@Enumerated(EnumType.STRING) //EnumType.ORDINAL -> 0 - 1 (index) değerlerini veritabanına yansıtır. Sıra bozulur ise veri de bozulur.
	private CurrencyType currencyType;
}
