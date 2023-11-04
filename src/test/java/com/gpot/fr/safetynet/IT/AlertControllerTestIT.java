package com.gpot.fr.safetynet.IT;

import static com.gpot.fr.safetynet.utils.AppUtils.getMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gpot.fr.safetynet.assembler.AlertAssembler;
import com.gpot.fr.safetynet.model.*;
import com.gpot.fr.safetynet.service.FireStationService;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import com.gpot.fr.safetynet.service.PersonService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AlertControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AlertAssembler alertAssembler;

  @Autowired
  private PersonService personService;

  @Autowired
  private FireStationService fireStationService;

  @Autowired
  private MedicalRecordsService medicalRecordsService;

  @Test
  void findPersonCoveredByStationTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/firestation").param("stationNumber", "2"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper().readValue(mvcResult.getResponse().getContentAsString(), CountAndAssembledList.class);
    assertEquals(4, result.getMajorCount());
    assertEquals(1, result.getMinorCount());
    assertEquals(5, result.getPersons().size());

    final var firstOnTheList = result.getPersons().get(0);
    assertEquals("Jonanathan", firstOnTheList.getFirstName());
    assertEquals("Marrack", firstOnTheList.getLastName());
    assertEquals("29 15th St", firstOnTheList.getAddress());
    assertEquals("841-874-6513", firstOnTheList.getPhone());
  }

  @Test
  void findChildByAddressTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/childAlert").param("address", "892 Downing Ct"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<ChildAndFamilyModel>>() {});
  }

  @Test
  void findPhoneByStationTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/phoneAlert").param("firestation", "4"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<String>>() {});
  }

  @Test
  void findInhabitantByAddressTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/fire").param("address", "892 Downing Ct"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), StationNumberAndAssembledList.class);
    assertEquals("2", result.getStationNumber());
    assertEquals(3, result.getInhabitantList().size());
  }

  @Test
  void findHomeByStationListTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/flood/stations").param("stations", "2").param("stations", "4"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper()
      .readerForListOf(HomeByStationListModel.class)
      .readValue(mvcResult.getResponse().getContentAsString());
    //  .readValue(mvcResult.getResponse().getContentAsString(), HomeByStationListModel.class);

  }

  @Test
  void findPersonInfoByNameTest() throws Exception {
    final var mvcResult = mockMvc
      .perform((get("/personInfo").param("firstName", "John")).param("lastName", "Boyd"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper().readValue(mvcResult.getResponse().getContentAsString(), PersonInfoModel.class);
  }

  @Test
  void findEmailByCityTest() throws Exception {
    final var mvcResult = mockMvc
      .perform(get("/communityEmail").param("city", "Culver"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final var result = getMapper().readValue(mvcResult.getResponse().getContentAsString(), String.class);
  }
}
