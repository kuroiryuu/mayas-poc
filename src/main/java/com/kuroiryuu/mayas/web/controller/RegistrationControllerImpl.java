package com.kuroiryuu.mayas.web.controller;

import com.kuroiryuu.mayas.dto.ResidentDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegistrationControllerImpl implements RegistrationController {
    @Override
    public String showRegistrationForm(WebRequest request, Model model) {
        ResidentDto residentDto = new ResidentDto();
        model.addAttribute("resident", residentDto);
        return "signup";
    }
}
