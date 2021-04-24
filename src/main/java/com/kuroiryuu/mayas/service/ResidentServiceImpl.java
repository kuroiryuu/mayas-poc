package com.kuroiryuu.mayas.service;

import com.kuroiryuu.mayas.dao.ResidentDao;
import com.kuroiryuu.mayas.dto.SignupRequest;
import com.kuroiryuu.mayas.model.Resident;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ModelMapper modelMapper;
    private final ResidentDao residentDao;

    public ResidentServiceImpl(ModelMapper modelMapper, ResidentDao residentDao) {
        this.modelMapper = modelMapper;
        this.residentDao = residentDao;
    }

    @Transactional
    @Override
    public Resident create(SignupRequest signupRequest) {
        return residentDao.save(modelMapper.map(signupRequest, Resident.class));
    }

    @Override
    public Optional<Resident> findByEmail(String email) {
        return residentDao.findOne(Example.of(new Resident().setEmail(email)));
    }

    @Override
    public List<Resident> findAll() {
        return residentDao.findAll();
    }

}
