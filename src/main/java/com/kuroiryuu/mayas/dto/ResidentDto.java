package com.kuroiryuu.mayas.dto;

import lombok.Data;

@Data
public class ResidentDto {
    private String email;
    private String firstName;
    private String lastName;
    private int apartment;
    private String phone;
}