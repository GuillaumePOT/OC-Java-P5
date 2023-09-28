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
public class InhabitantByAddressModel {

  private String firstName;
  private String lastName;
  private int age;
  private String phone;
  private List<String> allergies;
  private List<String> medication;
}
