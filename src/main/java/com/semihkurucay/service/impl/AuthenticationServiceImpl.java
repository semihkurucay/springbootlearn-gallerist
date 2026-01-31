package com.semihkurucay.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.semihkurucay.dto.AuthRequest;
import com.semihkurucay.dto.AuthResponse;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.RefreshTokenRequest;
import com.semihkurucay.entity.RefreshToken;
import com.semihkurucay.entity.User;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.exception.MessageType;
import com.semihkurucay.jwt.JwtService;
import com.semihkurucay.mapper.IUserMapper;
import com.semihkurucay.repository.IRefreshTokenRepository;
import com.semihkurucay.repository.IUserRepository;
import com.semihkurucay.service.IAuthenticationSrvice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationSrvice {

	private final IUserRepository userRepository;
	
	private final IRefreshTokenRepository refreshTokenRepository;
	
	private final IUserMapper userMapper;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final AuthenticationProvider authenticationProvider;
	
	private final JwtService jwtService;
	
	private User createUser(AuthRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		user.setCreateTime(new Date());
		
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	
	@Override
	public DtoUser register(AuthRequest request) {

		if(userRepository.existsByUsername(request.getUsername())) {
			throw new BaseException(new ErrorMessage(request.getUsername(), MessageType.RECURRENT_USERNAME));
		}
		
		return userMapper.toDto(userRepository.save(createUser(request)));
	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		// TODO Auto-generated method stub
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());
			
			String accessToken = jwtService.generateToken(optionalUser.get());
			
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
		}
	}

	private boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		// TODO Auto-generated method stub
		Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if(optionalRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(request.getRefreshToken(), MessageType.REFRESH_TOKEN_NOT_FOUND));
		}
		
		RefreshToken refreshToken = optionalRefreshToken.get();
		
		if(!isValidRefreshToken(optionalRefreshToken.get().getExpireDate())) {
			throw new BaseException(new ErrorMessage(request.getRefreshToken(), MessageType.REFRESH_TOKEN_IS_EXPIRED));
		}
		
		User user = optionalRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken saveRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

		return new AuthResponse(accessToken, saveRefreshToken.getRefreshToken());
	}
	
}
