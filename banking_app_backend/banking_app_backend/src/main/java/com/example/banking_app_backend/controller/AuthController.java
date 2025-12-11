package com.example.banking_app_backend.controller;

import com.example.banking_app_backend.dto.login.LoginRequest;
import com.example.banking_app_backend.dto.login.LoginResponse;
import com.example.banking_app_backend.dto.register.RegisterRequest;
import com.example.banking_app_backend.dto.register.RegisterResponse;
import com.example.banking_app_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest.getUserName(), registerRequest.getPassword());
        RegisterResponse registerResponse = new RegisterResponse();
        return registerResponse;
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        boolean result = authService.login(loginRequest.getUserName(), loginRequest.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        if(result){
            loginResponse.setResult(true);
            loginResponse.setMessage("User Logged in succesfully");
        }
        else{
            loginResponse.setResult(false);
            loginResponse.setMessage("Invalid Username or Password");
        }
        return loginResponse;
    }

}
