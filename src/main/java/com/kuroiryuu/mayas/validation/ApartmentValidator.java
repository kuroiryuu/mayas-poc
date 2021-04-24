package com.kuroiryuu.mayas.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ApartmentValidator implements ConstraintValidator<ValidApartment, Integer> {
    private static final String APARTMENT_PATTERN = "^[1-5]0[1-8]$";

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return Pattern.compile(APARTMENT_PATTERN).matcher(value.toString()).matches();
    }
}
