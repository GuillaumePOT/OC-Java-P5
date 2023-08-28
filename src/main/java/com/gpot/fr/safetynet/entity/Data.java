package com.gpot.fr.safetynet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class Data {
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecords> medicalrecords;
}
