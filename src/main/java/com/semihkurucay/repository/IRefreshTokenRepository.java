package com.semihkurucay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semihkurucay.entity.RefreshToken;


@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
