package com.gpot.fr.safetynet.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gpot.fr.safetynet.controller.FireStationController;
import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.repository.imp.DataRepository;
import com.gpot.fr.safetynet.service.FireStationService;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FireStationRootIT {

  @Autowired
  private FireStationController controller;

  @Autowired
  private FireStationService service;

  @BeforeEach
  void beforeEach() throws IOException {
    DataRepository.init();
    assertEquals(13, service.findAll().size());
  }

  @Test
  void itShouldTestSave() {
    assertEquals(13, service.findAll().size());
    FirestationDto testDto = FirestationDto.builder().address("address").station("1").build();
    controller.add(testDto);
    assertEquals(14, service.findAll().size());
    assertEquals("address", service.findAll().get(service.findAll().size() - 1).getAddress());
    assertEquals("1", service.findAll().get(service.findAll().size() - 1).getStation());
  }

  @Test
  void itShouldDelete() {
    assertEquals(13, service.findAll().size());
    FirestationDto testDto = FirestationDto.builder().address("address").station("1").build();
    controller.add(testDto);

    controller.delete("address");
    assertEquals(13, service.findAll().size());
  }

  @Test
  void itShouldUpdate() {
    assertEquals(13, service.findAll().size());
    FirestationDto testDto = FirestationDto.builder().address("address").station("1").build();
    controller.add(testDto);

    FirestationDto newDto = FirestationDto.builder().address("address").station("10").build();
    controller.update(newDto);
    assertEquals(14, service.findAll().size());
    assertEquals("address", service.findAll().get(service.findAll().size() - 1).getAddress());
    assertEquals("10", service.findAll().get(service.findAll().size() - 1).getStation());
  }
}
