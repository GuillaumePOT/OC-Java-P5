package com.gpot.fr.safetynet.service.imp;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersonService {
    private final PersonRepository personRepository;
    @Override
    public Person save(PersonDto dto) {
        final var person =  Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .city(dto.getCity())
                .zip(dto.getZip())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
        return personRepository.save(person);
    }
    @Override
    public void delete(String firstName, String lastName) {
        personRepository.delete(firstName,lastName);
    }
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    @Override
    public Person update(PersonDto dto) {
        final var person =  Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .city(dto.getCity())
                .zip(dto.getZip())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
        personRepository.update(person);
        return person;
    }
}
