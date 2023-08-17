package com.gpot.fr.safetynet.entity;

import lombok.*;
import org.springframework.stereotype.Component;

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
