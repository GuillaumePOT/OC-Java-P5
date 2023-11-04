package com.gpot.fr.safetynet.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpot.fr.safetynet.assembler.AlertAssembler;
import com.gpot.fr.safetynet.service.FireStationService;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import com.gpot.fr.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AlertController.class)
class AlertControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FireStationService fireStationService;

  @MockBean
  private PersonService personService;

  @MockBean
  private MedicalRecordsService medicalRecordsService;

  @MockBean
  private AlertAssembler alertAssembler;

  @Test
  void findPersonCoveredByStationTest() throws Exception {
    mockMvc.perform(get("/firestation").param("stationNumber", anyString())).andDo(print()).andExpect(status().isOk());
    verify(fireStationService).findAddressByStationNumber(anyString());
    verify(personService).findPersonByAddressList(anyList());
    verify(medicalRecordsService).findRecordsByPersonList(anyList());
    verify(alertAssembler).toModelFindPersonsCoveredByStation(anyList());
    verify(alertAssembler).toModelCountAndAssembledList(anyInt(), anyInt(), anyList());
  }

  @Test
  void findChildByAddressTest() throws Exception {
    mockMvc.perform(get("/childAlert").param("address", anyString())).andDo(print()).andExpect(status().isOk());
    verify(personService).findPersonByAddress(anyString());
    verify(medicalRecordsService).findRecordsByPersonList(anyList());
    verify(alertAssembler).toModelFindChildByAddress(anyList(), anyList());
  }

  @Test
  void findPhoneByStationTest() throws Exception {
    mockMvc.perform(get("/phoneAlert").param("firestation", anyString())).andDo(print()).andExpect(status().isOk());
    verify(fireStationService).findAddressByStationNumber(anyString());
    verify(personService).findPhoneByStationList(anyList());
  }

  @Test
  void findInhabitantByAddressTest() throws Exception {
    mockMvc.perform(get("/fire").param("address", anyString())).andDo(print()).andExpect(status().isOk());
    verify(fireStationService).findStationNumberByAddress(anyString());
    verify(personService).findPersonByAddress(anyString());
    verify(medicalRecordsService).findRecordsByPersonList(anyList());
    verify(alertAssembler).toModelInhabitantByAddress(anyList(), anyList());
    verify(alertAssembler).toModelStationNumberAndAssembledList(any(), anyList());
  }

  @Test
  void findHomeByStationListTest() throws Exception {
    mockMvc
      .perform(get("/flood/stations").param("stations", "").param("stations", "1"))
      .andDo(print())
      .andExpect(status().isOk());
    verify(personService).findPersonByAddressList(anyList());
    verify(medicalRecordsService).findRecordsByPersonList(anyList());
    verify(alertAssembler).toModelFindHomeByStationList(anyList(), anyList(), anyList());
  }

  @Test
  void findPersonInfoByNameTest() throws Exception {
    mockMvc
      .perform(get("/personInfo").param("firstName", anyString()).param("lastName", anyString()))
      .andDo(print())
      .andExpect(status().isOk());
    verify(personService).findPersonsByFirstAndLastName(anyString(), anyString());
    verify(medicalRecordsService).findRecordsByPersonList(anyList());
    verify(alertAssembler).toModelPersonInfo(anyList(), anyList());
  }

  @Test
  void findEmailByCityTest() throws Exception {
    mockMvc.perform(get("/communityEmail").param("city", anyString())).andDo(print()).andExpect(status().isOk());
    verify(personService).findEmailByCity(anyString());
  }
}
