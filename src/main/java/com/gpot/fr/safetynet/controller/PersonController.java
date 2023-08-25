package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    @PostMapping("/person")
    public ResponseEntity<Person> add(@RequestBody final PersonDto dto){

        final var person = personService.save(dto);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
    @DeleteMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<Void> delete(@PathVariable(name = "firstName") String firstName,@PathVariable(name = "lastName") String lastName){
        personService.delete(firstName,lastName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/person")
    public  ResponseEntity<Person> update(@RequestBody final PersonDto dto){
        final var person = personService.update(dto);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }
}
