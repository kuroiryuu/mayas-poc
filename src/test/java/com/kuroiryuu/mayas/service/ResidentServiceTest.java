package com.kuroiryuu.mayas.service;

import com.kuroiryuu.mayas.config.ModelMapperConfig;
import com.kuroiryuu.mayas.dao.ResidentDao;
import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.model.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResidentServiceTest {

    @Spy
    private final PasswordEncoder spyPasswordEncoder = new BCryptPasswordEncoder(11);
    @Spy
    private final ModelMapper spyModelMapper = new ModelMapperConfig(spyPasswordEncoder).modelMapper();
    @Mock
    private ResidentDao mockResidentDao;

    @InjectMocks
    private ResidentServiceImpl service;

    @Test
    void createReturnsResidentWithNonNullId() {
        SignupRequest request = new SignupRequest()
                .setEmail("user@example.com")
                .setFirstName("User first name")
                .setLastName("User last name")
                .setPassword("thePassword")
                .setMatchingPassword("thePassword")
                .setApartment(101)
                .setPhone("5555555555");
        when(mockResidentDao.save(any()))
                .then(invocation -> invocation.<Resident>getArgument(0).setId(UUID.randomUUID()));

        Resident result = service.create(request);

        assertThat(result)
                .hasNoNullFieldsOrProperties()
                .isEqualToComparingOnlyGivenFields(
                        request,
                        "email",
                        "firstName",
                        "lastName",
                        "apartment",
                        "phone");
        assertThat(spyPasswordEncoder.matches(request.getPassword(), result.getPassword())).isTrue();
    }

    @Test
    void findByEmailReturnsEmptyOptionalWhenEmailNotFound() {
        Optional<Resident> result = service.findByEmail("user@example.com");

        assertThat(result).isEmpty();
    }

    @Test
    void findByEmailReturnsNonEmptyOptionalWhenEmailFound() {
        String email = "user@example.com";
        when(mockResidentDao.findOne(any())).thenReturn(Optional.of(new Resident().setId(UUID.randomUUID()).setEmail(email)));

        Optional<Resident> result = service.findByEmail(email);

        assertThat(result).hasValueSatisfying(resident -> assertThat(resident.getEmail()).isEqualTo(email));
    }

    @Test
    void findAllReturnsEmptyListWhenNoRecords() {
        assertThat(service.findAll()).isEmpty();
    }

    @Test
    void findAllReturnsExpectedWhenRecordsExist() {
        when(mockResidentDao.findAll()).thenReturn(List.of(new Resident(), new Resident(), new Resident()));

        assertThat(service.findAll()).hasSize(3);
    }
}