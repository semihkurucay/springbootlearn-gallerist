package com.semihkurucay.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NotEmpty
public class AuthResponse {

	private String accessToken;
	private String refreshToken;
}
