package com.kuroiryuu.mayas.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final ResidentService residentService;

    public CustomUserDetailsService(ResidentService residentService) {
        this.residentService = residentService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return residentService.findByEmail(email)
                .map(resident -> new User(resident.getEmail(), resident.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
