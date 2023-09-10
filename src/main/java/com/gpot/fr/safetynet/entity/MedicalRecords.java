package com.gpot.fr.safetynet.entity;

import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecords {

  private String firstName;
  private String lastName;

  @Setter
  private String birthdate;

  @Setter
  private List<String> medications;

  @Setter
  private List<String> allergies;
}
