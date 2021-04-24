package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.AuthenticationRequest;
import com.kuroiryuu.mayas.dto.AuthenticationResponse;
import com.kuroiryuu.mayas.service.CustomUserDetailsService;
import com.kuroiryuu.mayas.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerImpl implements LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    public LoginControllerImpl(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails)));
    }

}
