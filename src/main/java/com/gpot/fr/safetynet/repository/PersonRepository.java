package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.Person;
import java.util.List;
public interface PersonRepository {
    Person save(Person person);
    void delete(String firstName, String lastName);
    List<Person> findAll();
    Person update(Person person);
    List<String> findEmailByCity(String city);

    List<Person> findPersonByAddressList(List<String> addressList);
    List<Person> findPersonByAddress(String address);

    List<String> findPhoneByStation(List<String> addressList);

    List<Person> findPersonByFirstAndLastName(String firstName, String lastName);
}

