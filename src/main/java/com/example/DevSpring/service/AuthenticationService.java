package com.example.DevSpring.service;

import com.example.DevSpring.dto.AuthenticationRequest;
import com.example.DevSpring.dto.IntrospectRequest;
import com.example.DevSpring.dto.response.AuthenticationResponse;
import com.example.DevSpring.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(String username);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
