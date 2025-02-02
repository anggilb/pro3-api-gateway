package com.example.auth_service_api.service;

import com.example.common_library.commons.dtos.LoginRequest;
import com.example.common_library.commons.dtos.TokenResponse;
import com.example.common_library.commons.dtos.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
    TokenResponse loginUser(LoginRequest loginRequest);
}