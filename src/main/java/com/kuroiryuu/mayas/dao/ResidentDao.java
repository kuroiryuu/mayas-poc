package com.kuroiryuu.mayas.dao;

import com.kuroiryuu.mayas.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidentDao extends JpaRepository<Resident, UUID> {
}
