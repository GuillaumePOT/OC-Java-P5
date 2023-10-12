package com.gpot.fr.safetynet.repository.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicalRecordsRepositoryImpTest {

  private MedicalRecordsRepositoryImp repository;
  private static final List<MedicalRecords> initial = new ArrayList<>();

  @BeforeAll
  public static void beforeAll() throws IOException {
    DataRepository.init();
    initial.addAll(MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST);
  }

  @BeforeEach
  public void beforeEach() {
    repository = new MedicalRecordsRepositoryImp();
    MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.clear();
    MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.addAll(initial);
    assertEquals(23, MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.size());
  }

  @AfterEach
  public void afterEach() {
    MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.clear();
    repository = null;
  }

  @Test
  void itShouldSave() {
    repository.save(MedicalRecords.builder().build());
    assertEquals(24, MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.size());
  }

  @Test
  void itShouldDelete() {
    repository.delete("John", "Boyd");
    assertEquals(22, MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.size());
  }

  @Test
  void itShouldFindRecordsByPersonList() {
    final var listOfPerson = List.of(Person.builder().firstName("John").lastName("Boyd").build());
    final var result = repository.findRecordsByPersonList(listOfPerson);
    assertEquals("03/06/1984", result.get(0).getBirthdate());
  }

  @Test
  void itShouldFindAll() {
    final var result = repository.findAll();
    assertEquals(23, result.size());
  }

  @Test
  void itShouldUpdate() {
    final var medicalRecordsUpdated = MedicalRecords
      .builder()
      .firstName("John")
      .lastName("Boyd")
      .medications(Collections.singletonList("newMedication"))
      .build();
    repository.update(medicalRecordsUpdated);
    assertEquals(
      Collections.singletonList("newMedication"),
      MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST.get(0).getMedications()
    );
  }
}
