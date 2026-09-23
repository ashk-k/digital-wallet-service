package com.ashkaan.digital_wallet_service;

public class RegisterResponse {
    private Long userId;
    private String name;
    private String email;

    public RegisterResponse(Long userId, String name, String email) {
    this.userId = userId;
    this.name = name;
    this.email = email;
}

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
