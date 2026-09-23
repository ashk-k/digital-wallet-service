package com.ashkaan.digital_wallet_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig {
    
    @Bean 
    public PasswordEncoder passwordEncoder() { //Spring ko bolre h ki jb bhi pswrd encrypt/hash krna ho, toh tb PasswordEncoder use krna hoga
        return new BCryptPasswordEncoder(); //for password hashing we are using BCrypt
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/register").permitAll()
            .anyRequest().authenticated()
          );
          return http.build();
    }
}
