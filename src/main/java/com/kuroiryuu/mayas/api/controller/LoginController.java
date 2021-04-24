package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.AuthenticationRequest;
import com.kuroiryuu.mayas.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginController {
    @PostMapping("/api/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request);
}
