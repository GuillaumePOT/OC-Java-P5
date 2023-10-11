package com.gpot.fr.safetynet.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.*;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  @Test
  public void itShouldToModelInhabitantByAddress() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").phone("phone").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(Collections.singletonList("medication"))
        .allergies(Collections.singletonList("allergies"))
        .birthdate("01/01/1999")
        .build()
    );
    List<InhabitantByAddressModel> result = alertAssembler.toModelInhabitantByAddress(
      listOfPerson,
      listOfMedicalRecords
    );
    final var model = result.get(0);
    assertFalse(result.isEmpty());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("phone", model.getPhone());
    assertEquals(Collections.singletonList("medication"), model.getMedication());
    assertEquals(Collections.singletonList("allergies"), model.getAllergies());
    assertEquals(24, model.getAge());
  }

  @Test
  public void itShouldToModelFindHomeByStationList() {
    final var addressList = List.of("address");
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").phone("phone").address("address").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(Collections.singletonList("medication"))
        .allergies(Collections.singletonList("allergies"))
        .birthdate("01/01/1999")
        .build()
    );
    List<HomeByStationListModel> result = alertAssembler.toModelFindHomeByStationList(
      addressList,
      listOfPerson,
      listOfMedicalRecords
    );
    final var model = result.get(0);
    assertFalse(result.isEmpty());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
    assertEquals(Collections.singletonList("medication"), model.getMedication());
    assertEquals(Collections.singletonList("allergies"), model.getAllergies());
    assertEquals(24, model.getAge());
  }

  @Test
  public void itShouldToModelPersonInfo() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").email("email").address("address").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(Collections.singletonList("medication"))
        .allergies(Collections.singletonList("allergies"))
        .birthdate("01/01/1999")
        .build()
    );
    List<PersonInfoModel> result = alertAssembler.toModelPersonInfo(listOfPerson, listOfMedicalRecords);
    final var model = result.get(0);
    assertFalse(result.isEmpty());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("email", model.getEmail());
    assertEquals(Collections.singletonList("medication"), model.getMedication());
    assertEquals(Collections.singletonList("allergies"), model.getAllergies());
    assertEquals(24, model.getAge());
  }

  @Test
  public void itShouldToModelChildAndFamilyList() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate("01/01/1999").build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelChildAndFamilyList(listOfPerson, listOfMedicalRecords);
    final var model = result.get(0);
    assertFalse(result.isEmpty());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals(24, model.getAge());
  }

  @Test
  public void itShouldToModelCountAndAssembledList() {
    final var minorCount = 0;
    final var majorCount = 1;
    final var assembledList = List.of(
      FireStationNumberModel
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .address("address")
        .phone("phone")
        .build()
    );
    CountAndAssembledList result = alertAssembler.toModelCountAndAssembledList(minorCount, majorCount, assembledList);
    final var model = result.getFireStationNumberModelList().get(0);
    assertNotNull(result);
    assertEquals(0, result.getMinorCount());
    assertEquals(1, result.getMajorCount());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
  }

  @Test
  public void itShouldToModelStationNumberAndAssembledList() {
    final var stationNumber = "1";
    final var inhabitantList = List.of(
      InhabitantByAddressModel
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .phone("phone")
        .age(23)
        .medication(Collections.singletonList("medication"))
        .allergies(Collections.singletonList("allergies"))
        .build()
    );
    StationNumberAndAssembledList result = alertAssembler.toModelStationNumberAndAssembledList(
      stationNumber,
      inhabitantList
    );
    final var model = result.getInhabitantList().get(0);
    assertEquals("1", result.getStationNumber());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("phone", model.getPhone());
    assertEquals(23, model.getAge());
    assertEquals(Collections.singletonList("medication"), model.getMedication());
    assertEquals(Collections.singletonList("allergies"), model.getAllergies());
  }

  @Test
  public void itShouldToModelFindChildByAddress() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate("01/01/2010").build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelFindChildByAddress(listOfPerson, listOfMedicalRecords);
    assertFalse(result.isEmpty());
  }

  @Test
  public void itShouldToModelFindChildByAddressWithoutMinor() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate("01/01/1999").build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelFindChildByAddress(listOfPerson, listOfMedicalRecords);
    assertTrue(result.isEmpty());
  }
}
