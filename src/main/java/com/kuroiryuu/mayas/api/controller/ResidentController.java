package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.ResidentDto;
import com.kuroiryuu.mayas.model.Resident;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface ResidentController {
    @PostMapping
    ResponseEntity<Resident> registerUserAccount(@ModelAttribute("user") @Valid ResidentDto residentDto, HttpServletRequest request, Errors errors);

}
