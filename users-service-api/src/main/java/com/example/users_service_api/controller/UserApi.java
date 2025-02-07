package com.example.users_service_api.controller;

import com.example.users_service_api.commons.constants.ApiPathConstants;
import com.example.users_service_api.commons.dtos.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface UserApi {
    @GetMapping(value = "/get")
    ResponseEntity<UserDetails> getUser(
            @RequestAttribute("userIdRequest") Long userId
    );

    @PutMapping(value = "/put")
    ResponseEntity<Void> putUser(
            @RequestAttribute("userIdRequest") Long userId,
            @RequestBody UserRequest userRequest
    );

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deleteUser(
            @RequestAttribute("userIdRequest") Long userId
    );
}