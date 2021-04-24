package com.kuroiryuu.mayas.service;

import com.kuroiryuu.mayas.model.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock
    private ResidentService mockResidentService;
    @InjectMocks
    private CustomUserDetailsService service;
    private final String email = "user@example.com";

    @Test
    void loadUserByUsernameThrowsExceptionWhenEmailNotFound() {
        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> service.loadUserByUsername(email));
    }

    @Test
    void loadUserByUsernameReturnsExpectedWhenEmailFound() {
        String thePassword = "ThePassword";
        when(mockResidentService.findByEmail(email))
                .thenReturn(Optional.of(new Resident().setEmail(email).setPassword(thePassword)));
        UserDetails result = service.loadUserByUsername(email);

        assertThat(result.getUsername()).isEqualTo(email);
        assertThat(result.getPassword()).isEqualTo(thePassword);
        assertThat(result.isEnabled()).isTrue();
        assertThat(result.isAccountNonLocked()).isTrue();
    }
}