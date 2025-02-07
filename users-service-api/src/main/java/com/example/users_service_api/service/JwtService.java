package com.example.users_service_api.service;

import com.example.users_service_api.commons.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Long extractedUserId(String token);
}
