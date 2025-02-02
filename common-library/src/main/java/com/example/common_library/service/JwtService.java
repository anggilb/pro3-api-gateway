package com.example.common_library.service;

import com.example.common_library.commons.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Long extractedUserId(String token);
}
