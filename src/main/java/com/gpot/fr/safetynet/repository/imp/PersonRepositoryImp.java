package com.gpot.fr.safetynet.repository.imp;


import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImp extends DataRepository implements PersonRepository {
    protected static final List<Person> PERSON_LIST = new ArrayList<>();

    @Override
    public Person save(Person person) {
        PERSON_LIST.add(person);
        return person;
    }
    @Override
    public void delete(String firstName, String lastName) {
        PERSON_LIST.remove(PERSON_LIST.stream().filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null));
    }
    @Override
    public Person find(String firstName, String lastName) {
        for (Person person : PERSON_LIST) {
            if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                return person;
            }
        }
        return null;
    }
    @Override
    public int findID(String firstName, String lastName) {
        for (Person person : PERSON_LIST) {
            if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                return PERSON_LIST.indexOf(person);
            }
        }
        return -1;
    }
    @Override
    public List<Person> findAll() {
        return PERSON_LIST;
    }
    @Override
    public Person update(Person person) {
        PERSON_LIST.stream().filter(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName()) && p.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst().ifPresent(personFound -> {
                    personFound.setAddress(person.getAddress());
                    personFound.setCity(person.getCity());
                    personFound.setZip(person.getZip());
                    personFound.setPhone(person.getPhone());
                    personFound.setEmail(person.getEmail());
                });
        return person;
    }
}
