package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.MedicalRecords;

import java.util.List;

public interface MedicalRecordsRepository {
    MedicalRecords save(MedicalRecords medicalRecords);
    void delete(String firstName, String lastName);
    List<MedicalRecords> findAll();
    MedicalRecords update(MedicalRecords medicalRecords);
}
