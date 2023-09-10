package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MedicalRecordsController {

  private final MedicalRecordsService medicalRecordsService;

  @PostMapping("/medicalRecord")
  public ResponseEntity<MedicalRecords> add(@RequestBody final MedicalRecordsDto dto) {
    final var medicalRecords = medicalRecordsService.save(dto);
    return new ResponseEntity<>(medicalRecords, HttpStatus.CREATED);
  }

  @DeleteMapping("/medicalRecord/{lastName}/{firstName}")
  public ResponseEntity<Void> delete(
    @PathVariable(name = "firstName") String firstName,
    @PathVariable(name = "lastName") String lastName
  ) {
    medicalRecordsService.delete(firstName, lastName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/medicalRecord")
  public ResponseEntity<MedicalRecords> update(@RequestBody final MedicalRecordsDto dto) {
    final var medicalRecords = medicalRecordsService.update(dto);
    return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
  }
}
