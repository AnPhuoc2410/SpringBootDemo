package com.example.DevSpring.controllers;

import com.example.DevSpring.dto.ApiResponse;
import com.example.DevSpring.dto.LoginDTO;
import com.example.DevSpring.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginDTO>> authen(@RequestBody LoginDTO request) {
        boolean result = authService.authenticate(request);

        // Prepare the ApiResponse based on the authentication result
        ApiResponse<LoginDTO> apiResponse = new ApiResponse<>();

        if (result) {
            apiResponse.setResult(request);
            return ResponseEntity.ok(apiResponse);  // Return 200 OK with user data
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);  // Return 401 Unauthorized
        }
    }
}
