package com.gpot.fr.safetynet.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.FireStationNumberModel;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

public class AlertAssemblerTest {

  private AlertAssembler alertAssembler;

  @BeforeEach
  public void beforeEach() {
    alertAssembler = new AlertAssembler();
  }

  @AfterEach
  public void afterEach() {
    alertAssembler = null;
  }

  @Test
  public void itShouldToModelFindPersonsCoveredByStation() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").address("address").phone("phone").build()
    );
    List<FireStationNumberModel> result = alertAssembler.toModelFindPersonsCoveredByStation(listOfPerson);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
  }
}
