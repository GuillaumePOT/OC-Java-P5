package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.Person;
import java.util.List;
public interface PersonRepository {
    Person save(Person person);
    void delete(String firstName, String lastName);
    Person find(String firstName, String lastName);
    int findID(String firstName, String lastName);
    List<Person> findAll();

    Person update(Person person);
}

