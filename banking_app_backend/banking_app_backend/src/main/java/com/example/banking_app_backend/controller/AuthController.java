package com.example.banking_app_backend.controller;

import com.example.banking_app_backend.dto.login.LoginRequest;
import com.example.banking_app_backend.dto.login.LoginResponse;
import com.example.banking_app_backend.dto.register.RegisterRequest;
import com.example.banking_app_backend.dto.register.RegisterResponse;
import com.example.banking_app_backend.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();
        return registerResponse;
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        return loginResponse;
    }

}
