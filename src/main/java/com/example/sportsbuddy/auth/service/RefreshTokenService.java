package com.example.sportsbuddy.auth.service;

import com.example.sportsbuddy.auth.config.RefreshToken;
import com.example.sportsbuddy.auth.config.RefreshTokenRepository;
import com.example.sportsbuddy.sports.repository.UserRepository;
import com.example.sportsbuddy.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
	
	@Autowired
	RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public RefreshToken createRefreshToken(String email){
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			RefreshToken refreshToken = RefreshToken.builder()
					.userInfo(user)
					.token(UUID.randomUUID().toString())
					.expiryDate(Instant.now().plusMillis(600000))
					.build();
			return refreshTokenRepository.save(refreshToken);
		} else {
			// Handle case where user is not found (e.g., throw exception or return null)
			throw new RuntimeException("User with email " + email + " not found!");
		}
	}
	
	
	
	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken token){
		if(token.getExpiryDate().compareTo(Instant.now())<0){
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
		}
		return token;
	}
	
}
