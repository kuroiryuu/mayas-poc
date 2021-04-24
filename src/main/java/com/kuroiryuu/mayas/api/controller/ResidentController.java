package com.kuroiryuu.mayas.api.controller;

import com.kuroiryuu.mayas.dto.ResidentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ResidentController {
    @GetMapping
    ResponseEntity<List<ResidentDto>> getResidents();
}
