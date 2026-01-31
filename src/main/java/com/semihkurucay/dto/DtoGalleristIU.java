package com.semihkurucay.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristIU {

	@NotEmpty(message = "İsim alanı boş geçilemez")
	private String firstName;
	
	@NotEmpty(message = "Soyisim alanı boş geçilemez")
	private String lastName;
	
	@NotNull(message = "Address id boş geçilemez")
	private Long addressId;
}
