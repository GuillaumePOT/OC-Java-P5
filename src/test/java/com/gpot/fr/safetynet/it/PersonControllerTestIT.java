package com.gpot.fr.safetynet.it;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;
import static com.gpot.fr.safetynet.utils.AppUtils.getMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import com.gpot.fr.safetynet.utils.AppUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonRepository repository;

  @Test
  void itShouldSave() throws Exception {
    final String dto = asJson(
      PersonDto.builder().firstName("firstName").lastName("lastName").address("address").build()
    );
    List<Person> all = repository.findAll();
    assertEquals(23, all.size());
    final var mvcResult = mockMvc
      .perform(post("/person").content(dto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated())
      .andReturn();
    final var result = getMapper().readValue(mvcResult.getResponse().getContentAsString(), Person.class);
    assertEquals("firstName", result.getFirstName());
    assertEquals("lastName", result.getLastName());
    assertEquals("address", result.getAddress());

    Person lastPerson = all.get(all.size() - 1);
    assertEquals("firstName", lastPerson.getFirstName());
    assertEquals("lastName", lastPerson.getLastName());
    assertEquals("address", lastPerson.getAddress());
  }

  @Test
  void itShouldDelete() throws Exception {
    assertEquals(23, repository.findAll().size());
    mockMvc.perform(delete("/person/Boyd/John")).andDo(print()).andExpect(status().isNoContent());
    assertEquals(22, repository.findAll().size());
  }

  @Test
  void itShouldUpdate() throws Exception {
    itShouldSave();
    List<Person> all = repository.findAll();
    final String newDto = asJson(
      PersonDto.builder().firstName("firstName").lastName("lastName").address("newAddress").build()
    );
    final var mvcResult = mockMvc
      .perform(
        put("/person").content(newDto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = AppUtils.getMapper().readValue(mvcResult.getResponse().getContentAsString(), Person.class);

    assertEquals("firstName", result.getFirstName());
    assertEquals("lastName", result.getLastName());
    assertEquals("newAddress", result.getAddress());

    Person lastPerson = all.get(all.size() - 1);
    assertEquals("firstName", lastPerson.getFirstName());
    assertEquals("lastName", lastPerson.getLastName());
    assertEquals("newAddress", lastPerson.getAddress());
  }
}
