package com.gpot.fr.safetynet.assembler;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.model.FireStationNumberModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlertAssembler {
    public List<FireStationNumberModel> toModelFindPersonsCoveredByStation(List<Person> personList) {
        return personList.stream().map(this::toModelFindPersonsCoveredByStation).toList();
    }
    public FireStationNumberModel toModelFindPersonsCoveredByStation(Person person) {
        return FireStationNumberModel.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .phone(person.getPhone())
                .build();
    }

}
