package com.gpot.fr.safetynet.repository.imp;

import static org.junit.jupiter.api.Assertions.*;

import com.gpot.fr.safetynet.assembler.AlertAssembler;
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
  private static List<Person> initial = new ArrayList<>();

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
  void itShouldFindPersonByAddress() {}

  @Test
  void itShouldFindPhoneByStation() {}

  @Test
  void itShouldFindPersonByFirstAndLastName() {}
}
