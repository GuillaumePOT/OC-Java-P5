package com.gpot.fr.safetynet.it;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import com.gpot.fr.safetynet.repository.FireStationRepository;
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
class FireStationControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FireStationRepository repository;

  @Test
  void itShouldTestSave() throws Exception {
    final String dto = asJson(FirestationDto.builder().address("address").station("1").build());
    List<FireStation> all = repository.findAll();
    assertEquals(13, all.size());
    final var mvcResult = mockMvc
      .perform(
        post("/firestation").content(dto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isCreated())
      .andReturn();
    final var result = AppUtils.getMapper().readValue(mvcResult.getResponse().getContentAsString(), FireStation.class);
    assertEquals("1", result.getStation());
    assertEquals("address", result.getAddress());

    assertEquals(14, all.size());
    FireStation lastFireStation = all.get(all.size() - 1);
    assertEquals("address", lastFireStation.getAddress());
    assertEquals("1", lastFireStation.getStation());
  }

  @Test
  void itShouldDelete() throws Exception {
    assertEquals(13, repository.findAll().size());
    mockMvc.perform(delete("/firestation/1509 Culver St")).andDo(print()).andExpect(status().isNoContent());
    assertEquals(12, repository.findAll().size());
  }

  @Test
  void itShouldUpdate() throws Exception {
    itShouldTestSave();
    final String newDto = asJson(FirestationDto.builder().address("address").station("10").build());
    final var mvcResult = mockMvc
      .perform(
        put("/firestation").content(newDto).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = AppUtils.getMapper().readValue(mvcResult.getResponse().getContentAsString(), FireStation.class);
    assertEquals("10", result.getStation());
    assertEquals("address", result.getAddress());

    assertEquals(14, repository.findAll().size());
    assertEquals("address", repository.findAll().get(repository.findAll().size() - 1).getAddress());
    assertEquals("10", repository.findAll().get(repository.findAll().size() - 1).getStation());
  }
}
