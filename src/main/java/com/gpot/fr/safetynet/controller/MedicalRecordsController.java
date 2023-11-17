package com.gpot.fr.safetynet.controller;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class MedicalRecordsController {

  private final MedicalRecordsService medicalRecordsService;

  @PostMapping("/medicalRecord")
  public ResponseEntity<MedicalRecords> add(@RequestBody final MedicalRecordsDto dto) {
    log.info("User call to method add MedicalRecord");
    final var medicalRecords = medicalRecordsService.save(dto);
    log.info("New MedicalRecords added : " + asJson(dto));
    return new ResponseEntity<>(medicalRecords, HttpStatus.CREATED);
  }

  @DeleteMapping("/medicalRecord/{lastName}/{firstName}")
  public ResponseEntity<Void> delete(
    @PathVariable(name = "firstName") String firstName,
    @PathVariable(name = "lastName") String lastName
  ) {
    log.info("User call to method delete MedicalRecord");
    medicalRecordsService.delete(firstName, lastName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/medicalRecord")
  public ResponseEntity<MedicalRecords> update(@RequestBody final MedicalRecordsDto dto) {
    log.info("User call to method update MedicalRecord");
    final var medicalRecords = medicalRecordsService.update(dto);
    return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
  }
}
