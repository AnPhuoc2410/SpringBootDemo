package com.example.DevSpring.service;

import com.example.DevSpring.dto.LoginDTO;

public interface AuthenticationService {
    boolean authenticate(LoginDTO request);
}
