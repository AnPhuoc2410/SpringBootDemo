package com.example.DevSpring.service;

import com.example.DevSpring.dto.request.AuthenticationRequest;
import com.example.DevSpring.dto.request.IntrospectRequest;
import com.example.DevSpring.dto.response.AuthenticationResponse;
import com.example.DevSpring.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(String username);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
