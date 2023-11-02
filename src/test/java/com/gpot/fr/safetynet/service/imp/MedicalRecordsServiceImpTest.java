package com.gpot.fr.safetynet.service.imp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.MedicalRecordsRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsServiceImpTest {

  @InjectMocks
  private MedicalRecordsServiceImp service;

  @Mock
  private MedicalRecordsRepository repository;

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(MedicalRecordsDto.builder().build()));
    verify(repository).save(any());
  }

  @Test
  void itShouldDelete() {
    assertDoesNotThrow(() -> service.delete("firstName", "lastName"));
    verify(repository).delete(any(), any());
  }

  @Test
  void itShouldFindAll() {
    assertDoesNotThrow(() -> service.findAll());
    verify(repository).findAll();
  }

  @Test
  void itShouldUpdate() {
    assertDoesNotThrow(() -> service.update(MedicalRecordsDto.builder().build()));
    verify(repository).update(any());
  }

  @Test
  void itShouldFindRecordsByPersonList() {
    assertDoesNotThrow(() -> service.findRecordsByPersonList(List.of((Person.builder().build()))));
    verify(repository).findRecordsByPersonList(anyList());
  }
}
