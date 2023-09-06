package com.gpot.fr.safetynet.assembler;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.FireStationNumberModel;
import com.gpot.fr.safetynet.model.HomeByStationListModel;
import com.gpot.fr.safetynet.model.InhabitantByAddressModel;
import com.gpot.fr.safetynet.model.PersonInfoModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlertAssembler {

    public List<FireStationNumberModel> toModelFindPersonsCoveredByStation(List<Person> personList) {
        return personList.stream()
                .map(this::toModelFindPersonsCoveredByStation)
                .toList();
    }

    private FireStationNumberModel toModelFindPersonsCoveredByStation(Person person) {
        return FireStationNumberModel.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .phone(person.getPhone())
                .build();
    }

    public List<InhabitantByAddressModel> toModelInhabitantByAddress(List<Person> personList, List<MedicalRecords> recordsList) {
        final var inhabitantList = new ArrayList<InhabitantByAddressModel>();
        personList.forEach(person -> {
            recordsList.stream().filter(f -> f.getFirstName().equalsIgnoreCase(person.getFirstName()) && f.getLastName().equalsIgnoreCase(person.getLastName()))
                    .findFirst().ifPresent(recordFound -> {
                        inhabitantList.add(this.toModelInhabitantByAddress(person, recordFound));
                    });
        });
        return inhabitantList;
    }

    private InhabitantByAddressModel toModelInhabitantByAddress(Person person, MedicalRecords records) {
        return InhabitantByAddressModel.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .phone(person.getPhone())
                .medication(records.getMedications())
                .allergies(records.getAllergies())
                .birthdate(records.getBirthdate())
                .build();
    }

    public List<HomeByStationListModel> toModelFindHomeByStationList(List<String> addressList, List<Person> personList, List<MedicalRecords> medicalRecords) {
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
        return HomeByStationListModel.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .phone(person.getPhone())
                .birthdate(records.getBirthdate())
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
        return PersonInfoModel.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .birthdate(records.getBirthdate())
                .allergies(records.getAllergies())
                .medication(records.getMedications())
                .build();
    }
}
