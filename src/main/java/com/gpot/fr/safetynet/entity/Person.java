package com.gpot.fr.safetynet.entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Person {
    private String firstName;
    private String lastName;
    @Setter
    private String email;
    @Setter
    private String city;
    @Setter
    private String address;
    @Setter
    private String phone;
    @Setter
    private String zip;


}
