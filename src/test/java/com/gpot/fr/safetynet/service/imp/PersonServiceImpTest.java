package com.gpot.fr.safetynet.service.imp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.repository.PersonRepository;
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
  void itShouldDelete() {}

  @Test
  void itShouldFindAll() {}

  @Test
  void itShouldUpdate() {}

  @Test
  void itShouldFindEmailByCity() {}

  @Test
  void itShouldFindPersonByAddressList() {}

  @Test
  void findPersonByAddress() {}

  @Test
  void findPhoneByStationList() {}

  @Test
  void findPersonsByFirstAndLastName() {}
}
