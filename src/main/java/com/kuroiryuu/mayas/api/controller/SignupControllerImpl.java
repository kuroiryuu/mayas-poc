package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.dto.ResidentDto;
import com.kuroiryuu.mayas.service.ResidentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignupControllerImpl implements SignupController {
    private final ResidentService residentService;
    private final ModelMapper modelMapper;

    public SignupControllerImpl(ResidentService residentService, ModelMapper modelMapper) {
        this.residentService = residentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<ResidentDto> signupUser(@Valid SignupRequest signupRequest) {
        return ResponseEntity.ok(modelMapper.map(residentService.create(signupRequest), ResidentDto.class));
    }
}
