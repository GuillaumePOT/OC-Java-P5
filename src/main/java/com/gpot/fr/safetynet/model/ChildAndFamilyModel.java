package com.gpot.fr.safetynet.model;

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
}
