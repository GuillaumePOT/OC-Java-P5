package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import com.gpot.fr.safetynet.service.FireStationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FireStationController {

  private final FireStationService fireStationService;

  /*  @GetMapping("/firestation")
  public ResponseEntity<List<FireStation>> findAll() {
    final var fireStation = fireStationService.findAll();
    return new ResponseEntity<>(fireStation, HttpStatus.OK);
  }*/

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
