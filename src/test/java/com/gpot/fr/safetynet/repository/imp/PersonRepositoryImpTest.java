package com.gpot.fr.safetynet.repository.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gpot.fr.safetynet.entity.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonRepositoryImpTest {

  private PersonRepositoryImp repository;
  private static final List<Person> initial = new ArrayList<>();

  @BeforeAll
  public static void beforeAll() throws IOException {
    DataRepository.init();
    initial.addAll(PersonRepositoryImp.PERSON_LIST);
  }

  @BeforeEach
  public void beforeEach() {
    repository = new PersonRepositoryImp();
    PersonRepositoryImp.PERSON_LIST.clear();
    PersonRepositoryImp.PERSON_LIST.addAll(initial);
    assertEquals(23, PersonRepositoryImp.PERSON_LIST.size());
  }

  @AfterEach
  public void afterEach() {
    PersonRepositoryImp.PERSON_LIST.clear();
    repository = null;
  }

  @Test
  void itShouldSave() {
    repository.save(Person.builder().build());
    assertEquals(24, PersonRepositoryImp.PERSON_LIST.size());
  }

  @Test
  void itShouldDelete() {
    repository.delete("John", "Boyd");
    assertEquals(22, PersonRepositoryImp.PERSON_LIST.size());
  }

  @Test
  void itShouldFindAll() {
    final var result = repository.findAll();
    assertEquals(23, result.size());
  }

  @Test
  void itShouldFindEmailByCity() {
    final var result = repository.findEmailByCity("Culver");
    assertEquals(23, result.size());
  }

  @Test
  void itShouldFindPersonByAddressList() {
    List<String> addressList = new ArrayList<>();
    addressList.add("1509 Culver St");
    addressList.add("29 15th St");
    final var result = repository.findPersonByAddressList(addressList);
    assertEquals(6, result.size());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Boyd", result.get(0).getLastName());
  }

  @Test
  void itShouldFindPersonByAddress() {
    final var address = "1509 Culver St";
    final var result = repository.findPersonByAddress(address);
    assertEquals(5, result.size());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Boyd", result.get(0).getLastName());
  }

  @Test
  void itShouldFindPhoneByStation() {
    List<String> addressList = new ArrayList<>();
    addressList.add("1509 Culver St");
    addressList.add("29 15th St");
    final var result = repository.findPhoneByStation(addressList);
    assertEquals(6, result.size());
    assertEquals("841-874-6512", result.get(0));
  }

  @Test
  void itShouldUpdate() {
    final var personUpdated = Person
      .builder()
      .firstName("John")
      .lastName("Boyd")
      .address("1509 Culver St")
      .phone("newPhone")
      .build();
    repository.update(personUpdated);
    assertEquals("newPhone", PersonRepositoryImp.PERSON_LIST.get(0).getPhone());
  }

  @Test
  void itShouldFindPersonByFirstAndLastName() {
    final var result = repository.findPersonByFirstAndLastName("John", "Boyd");
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Boyd", result.get(0).getLastName());
    assertEquals("Culver", result.get(0).getCity());
    assertEquals("1509 Culver St", result.get(0).getAddress());
    assertEquals("841-874-6512", result.get(0).getPhone());
  }
}
