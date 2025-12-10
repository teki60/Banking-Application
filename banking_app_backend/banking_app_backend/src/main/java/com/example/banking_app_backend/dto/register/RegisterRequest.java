package com.example.banking_app_backend.dto.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {

    private String userName;

    private String password;

}
