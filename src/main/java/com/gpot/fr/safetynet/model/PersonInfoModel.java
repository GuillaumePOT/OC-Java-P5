package com.gpot.fr.safetynet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonInfoModel {

  private String firstName;
  private String lastName;
  private int age;
  private String address;
  private String email;
  private List<String> medication;
  private List<String> allergies;
}
