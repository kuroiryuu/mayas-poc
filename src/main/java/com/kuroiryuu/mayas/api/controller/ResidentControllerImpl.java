package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.ResidentDto;
import com.kuroiryuu.mayas.model.Resident;
import com.kuroiryuu.mayas.service.ResidentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/resident")
public class ResidentControllerImpl implements ResidentController {
    @Autowired
    private ResidentService residentService;

    @Override
    public ResponseEntity<Resident> registerUserAccount(ResidentDto residentDto, HttpServletRequest request, Errors errors) {
        return ResponseEntity.ok(residentService.create(residentDto));
    }
}
