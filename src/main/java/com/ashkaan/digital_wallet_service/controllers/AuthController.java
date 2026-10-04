package com.ashkaan.digital_wallet_service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashkaan.digital_wallet_service.AuthService;
import com.ashkaan.digital_wallet_service.JWTService;
import com.ashkaan.digital_wallet_service.RegisterRequest;
import com.ashkaan.digital_wallet_service.RegisterResponse;
import com.ashkaan.digital_wallet_service.LoginRequest;
import com.ashkaan.digital_wallet_service.LoginResponse;
import com.ashkaan.digital_wallet_service.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JWTService jwtService;

    public AuthController(AuthService authService, JWTService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        User savedUser = authService.register(request);

        return new RegisterResponse(
        savedUser.getId(),
        savedUser.getName(),
        savedUser.getEmail()
        );
    }

@PostMapping("/login")
public LoginResponse login(@RequestBody LoginRequest request) {

    User user = authService.login(request);

    String token = jwtService.generateToken(user.getEmail());

    return new LoginResponse(token);
}
}
