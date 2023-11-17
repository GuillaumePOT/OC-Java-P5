package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import java.util.List;

public interface MedicalRecordsRepository {
  /**
   * Save MedicalRecords
   * @param medicalRecords MedicalRecords
   * @return MedicalRecords
   */
  MedicalRecords save(MedicalRecords medicalRecords);

  /**
   * Delete MedicalRecords by First and last name
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
   * @param medicalRecords MedicalRecords
   * @return MedicalRecords
   */
  MedicalRecords update(MedicalRecords medicalRecords);

  /**
   * Find MedicalRecords by person list
   * @param personList List<Person>
   * @return  List<MedicalRecords>
   */
  List<MedicalRecords> findRecordsByPersonList(List<Person> personList);
}
