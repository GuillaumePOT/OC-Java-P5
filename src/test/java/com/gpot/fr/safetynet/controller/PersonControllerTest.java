package com.gpot.fr.safetynet.controller;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  void postPersonTest() throws Exception {
    PersonDto testDto = PersonDto.builder().firstName("FirstName").lastName("LastName").address("address").build();
    mockMvc
      .perform(post("/person").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    verify(personService).save(any());
  }

  @Test
  void deletePersonTest() throws Exception {
    mockMvc
      .perform(delete("/person").param("lastName", "firstName").param("firstName", "firstName"))
      .andDo(print())
      .andExpect(status().isNoContent());
    verify(personService).delete(any(), any());
  }

  @Test
  void putPersonTest() throws Exception {
    PersonDto testDto = PersonDto.builder().firstName("FirstName").lastName("LastName").address("address").build();
    mockMvc
      .perform(put("/person").content(asJson(testDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk());
    verify(personService).update(any());
  }
}
