package com.semihkurucay.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCarIU {

	@NotNull(message = "galleristId boş geçilemez")
	private Long galleristId;
	
	@NotNull(message = "carId boş geçilemez")
	private Long carId;
	
	@NotNull(message = "customerId boş geçilemez")
	private Long customerId;
}
