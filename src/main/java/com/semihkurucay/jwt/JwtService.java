package com.semihkurucay.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	// https://8gwifi.org/jwsgen.jsp -> HS256 -> semihkurucay 
	private static final String SECRET_KEY = "LEpxTR7L9I3eeJpv8tOc0OSOYGixUCnQAwXdzx5tFaw=";
	
	private Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes);
	}
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token).getBody();
	}
	
	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token);
		return claimsFunction.apply(claims);
	}
	
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	public Boolean isTokenValid(String token) {
		Date expireDate = exportToken(token, Claims::getExpiration);
		return expireDate.after(new Date());
	}
}
