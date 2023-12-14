package com.gpot.fr.safetynet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChildAndFamilyModel {

  private String firstName;
  private String lastName;
  private int age;
  private List<PersonModel> otherFamilyMember;

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class PersonModel {

    private String firstName;
    private String lastName;
  }
}
