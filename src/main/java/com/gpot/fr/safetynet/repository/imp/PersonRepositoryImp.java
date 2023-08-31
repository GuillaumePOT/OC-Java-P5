package com.gpot.fr.safetynet.repository.imp;


import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> findEmailByCity(String city) {
        return PERSON_LIST.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).map(Person::getEmail).collect(Collectors.toList());
    }

    @Override
    public List<Person> findPersonByAddress(List<String> addressList) {
        List<Person> personList = new ArrayList<>();
        addressList.forEach(address -> {
            PERSON_LIST.stream().filter(p -> p.getAddress().equalsIgnoreCase(address))
                    .forEach(personList::add);
        });
        return personList;
    }
    @Override
    public List<String> findPhoneByStation(List<String> addressList) {
        List<String> phoneList = new ArrayList<>();
        for (String address : addressList) {
            phoneList = PERSON_LIST.stream().filter(p -> p.getCity().equalsIgnoreCase(address)).map(Person::getPhone).collect(Collectors.toList());
        }
        return phoneList;
    }
}
