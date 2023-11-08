package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import com.gpot.fr.safetynet.service.FireStationService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class FireStationController {

  private final FireStationService fireStationService;

  @GetMapping("/firestations")
  public ResponseEntity<List<FireStation>> findAll() {
    log.info("User call to method findAll Firestation");
    List<FireStation> response = fireStationService.findAll();
    log.info("Firestations found");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/firestation")
  public ResponseEntity<FireStation> add(@RequestBody final FirestationDto dto) {
    final var fireStation = fireStationService.save(dto);
    return new ResponseEntity<>(fireStation, HttpStatus.CREATED);
  }

  @DeleteMapping("/firestation/{address}")
  public ResponseEntity<FireStation> delete(@PathVariable(name = "address") String address) {
    fireStationService.delete(address);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/firestation")
  public ResponseEntity<FireStation> update(@RequestBody final FirestationDto dto) {
    final var fireStation = fireStationService.update(dto);
    return new ResponseEntity<>(fireStation, HttpStatus.OK);
  }
}
