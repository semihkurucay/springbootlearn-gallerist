package com.semihkurucay.service;

import com.semihkurucay.dto.AuthRequest;
import com.semihkurucay.dto.AuthResponse;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.RefreshTokenRequest;

public interface IAuthenticationSrvice {

	public DtoUser register(AuthRequest request);
	public AuthResponse authenticate(AuthRequest authRequest);
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
