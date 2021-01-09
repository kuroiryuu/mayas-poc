package com.kuroiryuu.mayas.service;

import com.kuroiryuu.mayas.model.Resident;
import com.kuroiryuu.mayas.dto.ResidentDto;

public interface ResidentService {
    Resident create(ResidentDto residentDto);
}
