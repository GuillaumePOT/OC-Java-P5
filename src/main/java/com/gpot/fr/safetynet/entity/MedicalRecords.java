package com.gpot.fr.safetynet.entity;

import lombok.*;

import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecords {
    private String firstName;
    private String lastName;
    @Setter
    private String birthdate;
    @Setter
    private List<String> medications;
    @Setter
    private List<String> allergies;
}
