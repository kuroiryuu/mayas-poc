package com.kuroiryuu.mayas.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

public interface RegistrationController {
    @GetMapping("/signup")
    String showRegistrationForm(WebRequest request, Model model);
}
