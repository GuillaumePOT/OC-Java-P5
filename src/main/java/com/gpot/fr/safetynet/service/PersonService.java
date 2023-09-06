package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;

import java.util.List;

public interface PersonService {
    Person save(PersonDto dto);
    void delete(String firstName, String lastName);
    List<Person> findAll();
    Person update(PersonDto dto);
    List<String> findEmailByCity(String city);

    List<Person> findPersonByAddressList(List<String> addressList);
    List<Person> findPersonByAddress(String address);

    List<String> findPhoneByStation(List<String> addressList);

    List<Person> findPersonsByFirstAndLastName(String firstName, String lastName);
}
