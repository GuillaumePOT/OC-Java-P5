package com.gpot.fr.safetynet.entity;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Data {

  private List<Person> persons;
  private List<FireStation> firestations;
  private List<MedicalRecords> medicalrecords;
}
