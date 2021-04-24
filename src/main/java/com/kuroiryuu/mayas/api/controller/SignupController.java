package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.dto.ResidentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface SignupController {
    @PostMapping("/api/signup")
    ResponseEntity<ResidentDto> signupUser(@Valid @RequestBody SignupRequest signupRequest);
}
