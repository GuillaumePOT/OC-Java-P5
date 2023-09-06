package com.gpot.fr.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class HomeByStationListModel {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String birthdate;
    private List<String> allergies;
    private List<String> medication;

}
