package com.gpot.fr.safetynet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class PersonInfoModel {
    private String FirstName;
    private String LastName;
    private String address;
    private String birthdate;
    private List<String> medication;
    private List<String> allergies;
}
