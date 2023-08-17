package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.imp.PersonRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {

    public void save(Person person);

    void delete(String firstName, String lastName);

    void modify(String firstName, String lastName, String newAddressnString, String newCity, String newZip, String newPhone, String newEmail);
}
