package com.gpot.fr.safetynet.controller;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FireStationController.class)
class FireStationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FireStationService fireStationService;

  @Test
  void postFireStationTest() throws Exception {
    FirestationDto testDto = FirestationDto.builder().address("address").station("1").build();
    mockMvc
      .perform(post("/firestation").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    verify(fireStationService).save(any());
  }

  @Test
  void deleteFireStationTest() throws Exception {
    mockMvc.perform(delete("/firestation/addressToDelete")).andDo(print()).andExpect(status().isNoContent());
    verify(fireStationService).delete(any());
  }

  @Test
  void putFireStationTest() throws Exception {
    FirestationDto testDto = FirestationDto.builder().address("address").station("1").build();
    mockMvc
      .perform(put("/firestation").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk());
    verify(fireStationService).update(any());
  }
}
