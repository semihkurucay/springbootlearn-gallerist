package com.semihkurucay.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.semihkurucay.controller.IRestAuthenticationController;
import com.semihkurucay.controller.RestBaseController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.AuthRequest;
import com.semihkurucay.dto.AuthResponse;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.RefreshTokenRequest;
import com.semihkurucay.service.IAuthenticationSrvice;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	private final IAuthenticationSrvice authenticationSrvice;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
		return ok(authenticationSrvice.register(authRequest));
	}

	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
		return ok(authenticationSrvice.authenticate(authRequest));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
		return ok(authenticationSrvice.refreshToken(request));
	}

}
