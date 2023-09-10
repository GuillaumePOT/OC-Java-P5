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
  private List<FireStationNumberModel> fireStationNumberModelList;
}
