package com.gpot.fr.safetynet.service.imp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.repository.PersonRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceImpTest {

  @InjectMocks
  private PersonServiceImp service;

  @Mock
  private PersonRepository repository;

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(PersonDto.builder().build()));
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
    assertDoesNotThrow(() -> service.update(PersonDto.builder().build()));
    verify(repository).update(any());
  }

  @Test
  void itShouldFindEmailByCity() {
    assertDoesNotThrow(() -> service.findEmailByCity("city"));
    verify(repository).findEmailByCity(any());
  }

  @Test
  void itShouldFindPersonByAddressList() {
    assertDoesNotThrow(() -> service.findPersonByAddressList(List.of("address")));
    verify(repository).findPersonByAddressList(anyList());
  }

  @Test
  void findPersonByAddress() {
    assertDoesNotThrow(() -> service.findPersonByAddress("address"));
    verify(repository).findPersonByAddress(any());
  }

  @Test
  void findPhoneByStationList() {
    assertDoesNotThrow(() -> service.findPhoneByStationList(List.of("address")));
    verify(repository).findPhoneByStation(anyList());
  }

  @Test
  void findPersonsByFirstAndLastName() {
    assertDoesNotThrow(() -> service.findPersonsByFirstAndLastName("firstName", "lastName"));
    verify(repository).findPersonByFirstAndLastName(any(), any());
  }
}
