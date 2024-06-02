package com.example.sportsbuddy.auth.service;

import com.example.sportsbuddy.auth.config.JwtService;
import com.example.sportsbuddy.auth.config.RefreshToken;
import com.example.sportsbuddy.auth.controllers.AuthenticationRequest;
import com.example.sportsbuddy.auth.controllers.AuthenticationResponse;
import com.example.sportsbuddy.auth.controllers.RegisterRequest;
import com.example.sportsbuddy.user.Role;
import com.example.sportsbuddy.user.User;
import com.example.sportsbuddy.sports.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);


        var jwtToken =  jwtService.generateToken(user.getEmail());
        return AuthenticationResponse.builder().token(jwtToken).build();




    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        // responsible for authenticating user

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            return AuthenticationResponse.builder()
                    .accessToken(jwtService.generateToken(request.getEmail()))
                    .token(refreshToken.getToken())
                    .build();
        
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
//        var user = repository.findByEmail(request.getEmail()).orElseThrow();
//        var jwtToken =  jwtService.generateToken(user);
//        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}

