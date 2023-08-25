package com.gpot.fr.safetynet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Getter
public class MedicalRecordsDto {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}
