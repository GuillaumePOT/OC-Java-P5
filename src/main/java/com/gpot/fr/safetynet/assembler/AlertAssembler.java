package com.gpot.fr.safetynet.assembler;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class AlertAssembler {

  public List<FireStationNumberModel> toModelFindPersonsCoveredByStation(List<Person> personList) {
    return personList.stream().map(this::toModelFindPersonsCoveredByStation).toList();
  }

  private FireStationNumberModel toModelFindPersonsCoveredByStation(Person person) {
    return FireStationNumberModel
      .builder()
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .address(person.getAddress())
      .phone(person.getPhone())
      .build();
  }

  public List<InhabitantByAddressModel> toModelInhabitantByAddress(
    List<Person> personList,
    List<MedicalRecords> recordsList
  ) {
    final var inhabitantList = new ArrayList<InhabitantByAddressModel>();
    personList.forEach(person -> {
      recordsList
        .stream()
        .filter(f ->
          f.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
          f.getLastName().equalsIgnoreCase(person.getLastName())
        )
        .findFirst()
        .ifPresent(recordFound -> {
          inhabitantList.add(this.toModelInhabitantByAddress(person, recordFound));
        });
    });
    return inhabitantList;
  }

  private InhabitantByAddressModel toModelInhabitantByAddress(Person person, MedicalRecords records) {
    return InhabitantByAddressModel
      .builder()
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .phone(person.getPhone())
      .medication(records.getMedications())
      .allergies(records.getAllergies())
      .age(calculateAge(records))
      .build();
  }

  public List<HomeByStationListModel> toModelFindHomeByStationList(
    List<String> addressList,
    List<Person> personList,
    List<MedicalRecords> medicalRecords
  ) {
    final var homeList = new ArrayList<HomeByStationListModel>();
    addressList.forEach(address -> {
      final var listOfPerson = personList
        .stream()
        .filter(person -> person.getAddress().equalsIgnoreCase(address))
        .toList();
      listOfPerson.forEach(person -> {
        medicalRecords
          .stream()
          .filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName()))
          .findFirst()
          .ifPresent(m -> {
            homeList.add(this.toModelFindHomeByStationList(person, m));
          });
      });
    });
    return homeList;
  }

  private HomeByStationListModel toModelFindHomeByStationList(Person person, MedicalRecords records) {
    return HomeByStationListModel
      .builder()
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .address(person.getAddress())
      .phone(person.getPhone())
      .age(calculateAge(records))
      .allergies(records.getAllergies())
      .medication(records.getMedications())
      .build();
  }

  public List<PersonInfoModel> toModelPersonInfo(List<Person> personList, List<MedicalRecords> reccordList) {
    final var infoList = new ArrayList<PersonInfoModel>();
    personList.forEach(person -> {
      reccordList.forEach(record -> {
        infoList.add(this.toModelPersonInfo(person, record));
      });
    });
    return infoList;
  }

  private PersonInfoModel toModelPersonInfo(Person person, MedicalRecords records) {
    return PersonInfoModel
      .builder()
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .address(person.getAddress())
      .email(person.getEmail())
      .age(calculateAge(records))
      .allergies(records.getAllergies())
      .medication(records.getMedications())
      .build();
  }

  public List<ChildAndFamilyModel> toModelChildAndFamilyList(
    List<Person> personList,
    List<MedicalRecords> recordsList
  ) {
    final var childAndFamilyList = new ArrayList<ChildAndFamilyModel>();
    personList.forEach(person -> {
      recordsList
        .stream()
        .filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName()))
        .findFirst()
        .ifPresent(m -> {
          childAndFamilyList.add(this.toModelChildAndFamilyList(person, m));
        });
    });
    return childAndFamilyList;
  }

  private ChildAndFamilyModel toModelChildAndFamilyList(Person person, MedicalRecords records) {
    return ChildAndFamilyModel
      .builder()
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .age(calculateAge(records))
      .build();
  }

  public CountAndAssembledList toModelCountAndAssembledList(
    int minorCount,
    int majorCount,
    List<FireStationNumberModel> assembledList
  ) {
    return CountAndAssembledList
      .builder()
      .majorCount(majorCount)
      .minorCount(minorCount)
      .fireStationNumberModelList(assembledList)
      .build();
  }

  public StationNumberAndAssembledList toModelStationNumberAndAssembledList(
    String stationNumber,
    List<InhabitantByAddressModel> inhabitantList
  ) {
    return StationNumberAndAssembledList.builder().stationNumber(stationNumber).inhabitantList(inhabitantList).build();
  }

  public boolean isThereMinor(MedicalRecords records) {
    return calculateAge(records) <= 18;
  }

  public int calculateAge(MedicalRecords records) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate localDate = LocalDate.parse(records.getBirthdate(), formatter);
    Period age = Period.between(localDate, LocalDate.now());
    return age.getYears();
  }
}
