package com.gpot.fr.safetynet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class FirestationDto {

  private String address;
  private String station;
}
