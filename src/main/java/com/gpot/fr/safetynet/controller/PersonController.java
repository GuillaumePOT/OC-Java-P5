package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.imp.PersonRepositoryImp;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonRepositoryImp personRepositoryImp;

    @PostMapping("/person")
    //@ResponseBody
    public ResponseEntity<Person> add( String firstName,String lastName,String address ,String city,String zip,String phone,String email){
        final var person =  Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .city(city)
                .zip(zip)
                .phone(phone)
                .email(email)
                .build();
        personService.save(person);
        System.out.println(personRepositoryImp.getPersonList().size());
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
    @DeleteMapping("/person")
    //@ResponseBody
    public ResponseEntity<Person> delete(String firstName,String lastName){
        personService.delete(firstName,lastName);
        System.out.println(personRepositoryImp.getPersonList().size());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/person")
    public  ResponseEntity<Person> modify(String firstName,String lastName,String newAddress,String newCity,String newZip,String newPhone,String newEmail){
        personService.modify(firstName,lastName,newAddress,newCity,newZip,newPhone,newEmail);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
