package com.gpot.fr.safetynet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  public void postPersonTest() throws Exception {
    PersonDto testDto = PersonDto.builder().firstName("FirstName").lastName("LastName").address("address").build();
    mockMvc
      .perform(post("/person").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk());
    verify(personService).save(any());
  }

  @Test
  public void deletePersonTest() throws Exception {
    mockMvc.perform(delete("/person/lastName/firstName")).andDo(print()).andExpect(status().isNoContent());
    verify(personService).delete(any(), any());
  }

  @Test
  public void putPersonTest() throws Exception {
    PersonDto testDto = PersonDto.builder().firstName("FirstName").lastName("LastName").address("address").build();
    mockMvc
      .perform(put("/person").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk());
    verify(personService).update(any());
  }

  private String asJson(Object object) throws JsonProcessingException {
    final var mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }
}
