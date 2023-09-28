package com.gpot.fr.safetynet.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StationNumberAndAssembledList {

  private String stationNumber;
  private List<InhabitantByAddressModel> inhabitantList;
}
