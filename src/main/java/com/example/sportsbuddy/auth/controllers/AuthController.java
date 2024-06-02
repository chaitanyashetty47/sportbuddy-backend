package com.example.sportsbuddy.auth.controllers;

import com.example.sportsbuddy.auth.config.JwtService;
import com.example.sportsbuddy.auth.config.RefreshToken;
import com.example.sportsbuddy.auth.service.AuthenticationService;
import com.example.sportsbuddy.auth.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class    AuthController {
    
    private final RefreshTokenService refreshTokenService;
    
    private final JwtService jwtService;

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(
            @RequestBody AuthenticationRequest request
    ){

        return ResponseEntity.ok(service.authenticate(request));
    }
    
    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getEmail());
                    return AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

}
