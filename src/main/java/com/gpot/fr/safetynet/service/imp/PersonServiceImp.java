package com.gpot.fr.safetynet.service.imp;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersonService {
    private final PersonRepository personRepository;
    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(String firstName, String lastName) {
        personRepository.delete(firstName,lastName);
    }

    @Override
    public void modify(String firstName, String lastName, String newAddress, String newCity, String newZip, String newPhone, String newEmail) {
        personRepository.modify(firstName,lastName,newAddress,newCity,newZip,newPhone,newEmail);
    }
}
