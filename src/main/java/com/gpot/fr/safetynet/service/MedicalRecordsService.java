package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import java.util.List;

public interface MedicalRecordsService {
  /**
   * Save MedicalRecords
   * @param dto MedicalRecordsDto
   * @return MedicalRecords
   */
  MedicalRecords save(MedicalRecordsDto dto);

  /**
   * Delete MedicalRecords by first and last name
   * @param firstName String
   * @param lastName String
   */
  void delete(String firstName, String lastName);

  /**
   * Find all MedicalRecords
   * @return List<MedicalRecords>
   */
  List<MedicalRecords> findAll();

  /**
   * Update MedicalRecords
   * @param dto MedicalRecordsDto
   * @return MedicalRecords
   */
  MedicalRecords update(MedicalRecordsDto dto);

  /**
   * Find MedicalRecords by a list of persons
   * @param personList List<Person>
   * @return List<MedicalRecords>
   */
  List<MedicalRecords> findRecordsByPersonList(List<Person> personList);
}
