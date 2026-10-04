package com.ashkaan.digital_wallet_service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.JwtException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService){
        this.jwtService = jwtService;
    }

@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
      
      String authHeader = request.getHeader("Authorization");
      if(authHeader != null && authHeader.startsWith("Bearer ")) { 
        String token = authHeader.substring(7);

     try {
        String username = jwtService.extractUsername(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        java.util.List.of()
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        

    } catch (JwtException e) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }
}
  
      filterChain.doFilter(request, response);
    }
}

