package com.gpot.fr.safetynet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicalRecordsController.class)
public class MedicalRecordsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MedicalRecordsService medicalRecordsService;

  @Test
  public void postMedicalRecordTest() throws Exception {
    MedicalRecordsDto testDto = MedicalRecordsDto.builder().firstName("FirstName").lastName("LastName").build();
    mockMvc
      .perform(post("/medicalRecord").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    verify(medicalRecordsService).save(any());
  }

  @Test
  public void deleteMedicalRecordTest() throws Exception {
    mockMvc.perform(delete("/medicalRecord/lastName/firstName")).andDo(print()).andExpect(status().isNoContent());
    verify(medicalRecordsService).delete(any(), any());
  }

  @Test
  public void putMedicalRecordTest() throws Exception {
    MedicalRecordsDto testDto = MedicalRecordsDto.builder().firstName("FirstName").lastName("LastName").build();
    mockMvc
      .perform(put("/medicalRecord").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk());
    verify(medicalRecordsService).update(any());
  }

  private String asJson(Object object) throws JsonProcessingException {
    final var mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }
}
