package com.semihkurucay.entity;

import java.math.BigDecimal;

import com.semihkurucay.enums.CarStatusType;
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
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {
	
	@Column(name = "plaka")
	private String plaka;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "production_year")
	private Integer productionYear;
	
	@Column(name = "km")
	private Long km;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "damege_amount")
	private BigDecimal damegeAmount;
	
	@Column(name = "currencyType")
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
	
	@Column(name = "car_status_type")
	@Enumerated(EnumType.STRING)
	private CarStatusType carStatusType;
}
