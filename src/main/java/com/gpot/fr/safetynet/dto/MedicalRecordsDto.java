package com.gpot.fr.safetynet.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MedicalRecordsDto {

  private String firstName;
  private String lastName;
  private String birthdate;
  private List<String> medications;
  private List<String> allergies;
}
