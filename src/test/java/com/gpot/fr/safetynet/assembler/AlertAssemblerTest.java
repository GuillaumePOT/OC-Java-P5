package com.gpot.fr.safetynet.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlertAssemblerTest {

  private AlertAssembler alertAssembler;

  final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  private final String majorDate = LocalDate.now().minusYears(20).format(pattern);
  private final String minorDate = LocalDate.now().minusYears(16).format(pattern);

  @BeforeEach
  public void beforeEach() {
    alertAssembler = new AlertAssembler();
  }

  @AfterEach
  public void afterEach() {
    alertAssembler = null;
  }

  @Test
  void itShouldToModelFindPersonsCoveredByStation() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").address("address").phone("phone").build()
    );
    List<CountAndAssembledList.FireStationNumberModel> result = alertAssembler.toModelFindPersonsCoveredByStation(
      listOfPerson
    );
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
  }

  @Test
  void itShouldToModelInhabitantByAddress() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").phone("phone").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(List.of("medication"))
        .allergies(List.of("allergies"))
        .birthdate(majorDate)
        .build()
    );
    List<InhabitantByAddressModel> result = alertAssembler.toModelInhabitantByAddress(
      listOfPerson,
      listOfMedicalRecords
    );
    assertFalse(result.isEmpty());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("phone", model.getPhone());
    assertEquals(List.of("medication"), model.getMedication());
    assertEquals(List.of("allergies"), model.getAllergies());
    assertEquals(20, model.getAge());
  }

  @Test
  void itShouldToModelFindHomeByStationList() {
    final var addressList = List.of("address");
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").phone("phone").address("address").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(List.of("medication"))
        .allergies(List.of("allergies"))
        .birthdate(majorDate)
        .build()
    );
    List<HomeByStationListModel> result = alertAssembler.toModelFindHomeByStationList(
      addressList,
      listOfPerson,
      listOfMedicalRecords
    );
    assertFalse(result.isEmpty());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
    assertEquals(List.of("medication"), model.getMedication());
    assertEquals(List.of("allergies"), model.getAllergies());
    assertEquals(20, model.getAge());
  }

  @Test
  void itShouldToModelPersonInfo() {
    final var listOfPerson = List.of(
      Person.builder().firstName("firstName").lastName("lastName").email("email").address("address").build()
    );
    final var listOfMedicalRecords = List.of(
      MedicalRecords
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .medications(List.of("medication"))
        .allergies(List.of("allergies"))
        .birthdate(majorDate)
        .build()
    );
    List<PersonInfoModel> result = alertAssembler.toModelPersonInfo(listOfPerson, listOfMedicalRecords);
    assertFalse(result.isEmpty());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("email", model.getEmail());
    assertEquals(List.of("medication"), model.getMedication());
    assertEquals(List.of("allergies"), model.getAllergies());
    assertEquals(20, model.getAge());
  }

  @Test
  void itShouldToModelChildAndFamilyList() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate(minorDate).build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelFindChildByAddress(listOfPerson, listOfMedicalRecords);
    assertFalse(result.isEmpty());
    final var model = result.get(0);
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals(16, model.getAge());
  }

  @Test
  void itShouldToModelCountAndAssembledList() {
    final var minorCount = 0;
    final var majorCount = 1;
    final var assembledList = List.of(
      CountAndAssembledList.FireStationNumberModel
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .address("address")
        .phone("phone")
        .build()
    );
    CountAndAssembledList result = alertAssembler.toModelCountAndAssembledList(minorCount, majorCount, assembledList);
    final var model = result.getPersons().get(0);
    assertNotNull(result);
    assertEquals(0, result.getMinorCount());
    assertEquals(1, result.getMajorCount());
    assertEquals("firstName", model.getFirstName());
    assertEquals("lastName", model.getLastName());
    assertEquals("address", model.getAddress());
    assertEquals("phone", model.getPhone());
  }

  @Test
  void itShouldToModelStationNumberAndAssembledList() {
    final var stationNumber = "1";
    final var inhabitantList = List.of(
      InhabitantByAddressModel
        .builder()
        .firstName("firstName")
        .lastName("lastName")
        .phone("phone")
        .age(20)
        .medication(List.of("medication"))
        .allergies(List.of("allergies"))
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
    assertEquals(20, model.getAge());
    assertEquals(List.of("medication"), model.getMedication());
    assertEquals(List.of("allergies"), model.getAllergies());
  }

  @Test
  void itShouldToModelFindChildByAddress() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate(minorDate).build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelFindChildByAddress(listOfPerson, listOfMedicalRecords);
    assertFalse(result.isEmpty());
  }

  @Test
  void itShouldToModelFindChildByAddressWithoutMinor() {
    final var listOfPerson = List.of(Person.builder().firstName("firstName").lastName("lastName").build());
    final var listOfMedicalRecords = List.of(
      MedicalRecords.builder().firstName("firstName").lastName("lastName").birthdate("01/01/1999").build()
    );
    List<ChildAndFamilyModel> result = alertAssembler.toModelFindChildByAddress(listOfPerson, listOfMedicalRecords);
    assertTrue(result.isEmpty());
  }
}
