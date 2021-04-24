package com.kuroiryuu.mayas.dto;

import com.kuroiryuu.mayas.validation.PasswordMatches;
import com.kuroiryuu.mayas.validation.ValidApartment;
import com.kuroiryuu.mayas.validation.ValidPhone;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@PasswordMatches
public class SignupRequest {
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    @ValidApartment
    private int apartment;
    @ValidPhone
    private String phone;
}
