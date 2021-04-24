package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.ResidentDto;
import com.kuroiryuu.mayas.service.ResidentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/residents")
public class ResidentControllerImpl implements ResidentController {
    private final ResidentService residentService;
    private final ModelMapper modelMapper;

    public ResidentControllerImpl(ResidentService residentService, ModelMapper modelMapper) {
        this.residentService = residentService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<ResidentDto>> getResidents() {
        return ResponseEntity.ok(residentService.findAll().stream()
                .map(resident -> modelMapper.map(resident, ResidentDto.class))
                .collect(Collectors.toUnmodifiableList()));
    }
}
