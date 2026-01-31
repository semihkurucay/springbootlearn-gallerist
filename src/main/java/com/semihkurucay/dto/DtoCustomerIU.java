package com.semihkurucay.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomerIU {

	@NotEmpty(message = "İsim alanı boş geçilemez")
	private String firstName;

	@NotEmpty(message = "Soyisim alanı boş geçilemez")
	private String lastName;

	@NotEmpty(message = "TC bilgisi boş geçilemez")
	@Pattern(regexp = "^[0-9]+$", message = "Sadece sayı girin")
	@Size(min = 11, max = 11, message = "TC bilgisi 11 hane olmalı")
	private String tckn;

	@NotNull(message = "Doğum tarihi boş geçilemez")
	private Date birthOfDate;

	@NotNull(message = "Address id boş geçilemez")
	private Long addressId;

	@NotNull(message = "Account id boş geçilemez")
	private Long accountId;
}
