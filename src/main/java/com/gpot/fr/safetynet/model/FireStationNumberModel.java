package com.gpot.fr.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class FireStationNumberModel {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}
