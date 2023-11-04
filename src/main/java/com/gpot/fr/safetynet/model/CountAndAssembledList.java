package com.gpot.fr.safetynet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountAndAssembledList {

  private int minorCount;
  private int majorCount;
  private List<FireStationNumberModel> persons;

  @NoArgsConstructor
  @Getter
  @AllArgsConstructor
  @Builder
  public static class FireStationNumberModel {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
  }
}
