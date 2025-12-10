package com.example.banking_app_backend.service;

public interface AuthService {

    void register(String userName, String password);

    boolean login(String userName, String password);

}
