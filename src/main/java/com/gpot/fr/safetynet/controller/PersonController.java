package com.gpot.fr.safetynet.controller;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class PersonController {

  private final PersonService personService;

  @PostMapping("/person")
  public ResponseEntity<Person> add(@RequestBody final PersonDto dto) {
    log.info("User call to method add Person");
    final var person = personService.save(dto);
    log.info("New Person added : " + asJson(dto));
    return new ResponseEntity<>(person, HttpStatus.CREATED);
  }

  @DeleteMapping("/person/{lastName}/{firstName}")
  public ResponseEntity<Void> delete(
    @PathVariable(name = "firstName") String firstName,
    @PathVariable(name = "lastName") String lastName
  ) {
    log.info("User call to method delete Person");
    personService.delete(firstName, lastName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/person")
  public ResponseEntity<Person> update(@RequestBody final PersonDto dto) {
    log.info("User call to method update Person");
    final var person = personService.update(dto);
    return new ResponseEntity<>(person, HttpStatus.OK);
  }
}
