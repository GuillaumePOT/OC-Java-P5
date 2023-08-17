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
    public void save(Person person) {
        PERSON_LIST.add(person);
    }

    @Override
    public void delete(String firstName, String lastName) {
        PERSON_LIST.remove(find(firstName, lastName));
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
    public int findID(String firstName, String lastName)  {
            for (Person person : PERSON_LIST) {
                if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                    return PERSON_LIST.indexOf(person);
                }
            }
        return -1;
    }
    @Override
    public void modify(String firstName, String lastName, String newAddress, String newCity, String newZip, String newPhone, String newEmail) {
        Person person = new Person(firstName,lastName,newAddress,newCity,newZip,newPhone,newEmail);
        PERSON_LIST.set(findID(firstName,lastName),person);
    }
    public List<Person> getPersonList() {
        return PERSON_LIST;
    }
}
