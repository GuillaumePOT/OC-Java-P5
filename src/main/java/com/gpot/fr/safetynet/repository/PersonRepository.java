package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.Person;

public interface PersonRepository {

    void save(Person person);

    void delete(String firstName, String lastName);
    Person find(String firstName, String lastName);

    void modify(String firstName, String lastName, String newAddress, String newCity, String newZip, String newPhone, String newEmail);
    int findID(String firstName, String lastName) throws Exception;
}

