package com.gpot.fr.safetynet.it;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;
import static com.gpot.fr.safetynet.utils.AppUtils.getMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.dto.MedicalRecordsDto;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.repository.MedicalRecordsRepository;
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
class MedicalRecordsControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MedicalRecordsRepository repository;

  @Test
  void itShouldSave() throws Exception {
    final String dto = asJson(
      MedicalRecordsDto.builder().firstName("firstName").lastName("lastName").birthdate("birthdate").build()
    );
    List<MedicalRecords> all = repository.findAll();
    assertEquals(23, all.size());
    final var mvcResult = mockMvc
      .perform(
        post("/medicalRecord").content(dto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isCreated())
      .andReturn();
    final var result = getMapper().readValue(mvcResult.getResponse().getContentAsString(), MedicalRecords.class);
    assertEquals("firstName", result.getFirstName());
    assertEquals("lastName", result.getLastName());
    assertEquals("birthdate", result.getBirthdate());

    MedicalRecords lastMedicalRecords = all.get(all.size() - 1);
    assertEquals("firstName", lastMedicalRecords.getFirstName());
    assertEquals("lastName", lastMedicalRecords.getLastName());
    assertEquals("birthdate", lastMedicalRecords.getBirthdate());
  }

  @Test
  void itShouldDelete() throws Exception {
    assertEquals(23, repository.findAll().size());
    mockMvc.perform(delete("/medicalRecord/Boyd/John")).andDo(print()).andExpect(status().isNoContent());
    assertEquals(22, repository.findAll().size());
  }

  @Test
  void itShouldUpdate() throws Exception {
    itShouldSave();
    List<MedicalRecords> all = repository.findAll();
    final String newDto = asJson(
      MedicalRecordsDto.builder().firstName("firstName").lastName("lastName").birthdate("newBirthdate").build()
    );
    final var mvcResult = mockMvc
      .perform(
        put("/medicalRecord").content(newDto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = AppUtils
      .getMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), MedicalRecords.class);

    assertEquals("firstName", result.getFirstName());
    assertEquals("lastName", result.getLastName());
    assertEquals("newBirthdate", result.getBirthdate());

    MedicalRecords lastMedicalRecords = all.get(all.size() - 1);
    assertEquals("firstName", lastMedicalRecords.getFirstName());
    assertEquals("lastName", lastMedicalRecords.getLastName());
    assertEquals("newBirthdate", lastMedicalRecords.getBirthdate());
  }
}
