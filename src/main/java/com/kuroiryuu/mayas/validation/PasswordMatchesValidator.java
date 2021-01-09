package com.kuroiryuu.mayas.validation;

import com.kuroiryuu.mayas.dto.ResidentDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ResidentDto> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(ResidentDto user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
