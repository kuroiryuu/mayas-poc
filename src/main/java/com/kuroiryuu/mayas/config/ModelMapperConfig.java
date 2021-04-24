package com.kuroiryuu.mayas.config;

import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.model.Resident;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
public class ModelMapperConfig {
    private final PasswordEncoder passwordEncoder;

    public ModelMapperConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(SignupRequest.class, Resident.class)
                .addMappings(mapper -> mapper
                        .using((MappingContext<String, String> ctx) -> Objects.isNull(ctx.getSource()) ? null : passwordEncoder.encode(ctx.getSource()))
                        .map(SignupRequest::getPassword, Resident::setPassword));
        return modelMapper;
    }
}
