package com.gpot.fr.safetynet.service.imp;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.MedicalRecordsRepository;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordsServiceImp implements MedicalRecordsService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    @Override
    public MedicalRecords save(MedicalRecordsDto dto) {
        final var medicalRecords =  MedicalRecords.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .medications(dto.getMedications())
                .allergies(dto.getAllergies())
                .birthdate(dto.getBirthdate())
                .build();
        return medicalRecordsRepository.save(medicalRecords);
    }
    @Override
    public void delete(String firstName, String lastName) {
        medicalRecordsRepository.delete(firstName, lastName);
    }
    @Override
    public List<MedicalRecords> findAll() {
        return medicalRecordsRepository.findAll();
    }
    @Override
    public MedicalRecords update(MedicalRecordsDto dto) {
        final var medicalRecords =  MedicalRecords.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .medications(dto.getMedications())
                .allergies(dto.getAllergies())
                .birthdate(dto.getBirthdate())
                .build();
        return medicalRecordsRepository.update(medicalRecords);
    }

    @Override
    public List<MedicalRecords> findRecordsByPersonList(List<Person> personList) {
        return medicalRecordsRepository.findRecordsByPersonList(personList);
    }
}
