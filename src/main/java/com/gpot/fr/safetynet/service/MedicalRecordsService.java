package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import java.util.List;
public interface MedicalRecordsService {
    MedicalRecords save(MedicalRecordsDto dto);
    void delete(String firstName, String lastName);
    List<MedicalRecords> findAll();
    MedicalRecords update(MedicalRecordsDto dto);
}
