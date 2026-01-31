package com.semihkurucay.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddressIU {
	
	@NotEmpty(message = "Şehir alanı boş geçilemez")
	@Pattern(regexp = "^[a-zA-ZüğışçöÜĞİŞÇÖ ]+$", message = "Sadece harf girin")
	private String city;
	
	@NotEmpty(message = "İlçe alanı boş geçilemez")
	@Pattern(regexp = "^[a-zA-ZüğışçöÜĞİŞÇÖ ]+$", message = "Sadece harf girin")
	private String district;
	
	@NotEmpty(message = "Mahalle alanı boş geçilemez")
	private String neighborhood;
	
	@NotEmpty(message = "Sokak alanı boş geçilemez")
	private String street;
}
