package com.kuroiryuu.mayas.validation;

import com.kuroiryuu.mayas.dto.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        SignupRequest signupRequest = (SignupRequest) obj;
        return Objects.equals(signupRequest.getPassword(), signupRequest.getMatchingPassword());
    }
}
