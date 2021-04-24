package com.kuroiryuu.mayas.service;

import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.model.Resident;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    Resident create(SignupRequest signupRequest);

    Optional<Resident> findByEmail(String email);

    List<Resident> findAll();
}
