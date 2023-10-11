package com.gpot.fr.safetynet.service.imp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceImpTest {

  @InjectMocks
  private FireStationServiceImp service;

  @Mock
  private FireStationRepository repository;

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(FirestationDto.builder().build()));
    verify(repository).save(any());
  }

  @Test
  void itShouldDelete() {
    assertDoesNotThrow(() -> service.delete(any()));
    verify(repository).delete(any());
  }

  @Test
  void itShouldUpdate() {
    assertDoesNotThrow(() -> service.update(FirestationDto.builder().build()));
    verify(repository).update(any());
  }

  @Test
  void itShouldFindAll() {
    assertDoesNotThrow(() -> service.findAll());
    verify(repository).findAll();
  }

  @Test
  void itShouldFindAddressByStationNumber() {
    assertDoesNotThrow(() -> service.findAddressByStationNumber("stationNumber"));
    verify(repository).findAddressByStationNumber(any());
  }

  @Test
  void itShouldFindStationNumberByAddress() {
    assertDoesNotThrow(() -> service.findStationNumberByAddress("address"));
    verify(repository).findStationNumberByAddress(any());
  }
}
