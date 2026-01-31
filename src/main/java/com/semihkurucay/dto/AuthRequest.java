package com.semihkurucay.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

	@NotEmpty(message = "Username alanı boş geçilemez")
	private String username;
	
	@NotEmpty(message = "Password alanı boş geçilemez")
	private String password;
}
