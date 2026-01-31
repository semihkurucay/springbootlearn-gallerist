package com.semihkurucay.controller;

import com.semihkurucay.dto.AuthRequest;
import com.semihkurucay.dto.AuthResponse;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
	public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
