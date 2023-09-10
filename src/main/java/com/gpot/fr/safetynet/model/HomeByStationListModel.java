package com.gpot.fr.safetynet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class HomeByStationListModel {

  private String firstName;
  private String lastName;
  private int age;
  private String phone;
  private String address;
  private List<String> allergies;
  private List<String> medication;
}
