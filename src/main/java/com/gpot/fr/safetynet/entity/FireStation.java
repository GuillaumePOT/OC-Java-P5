package com.gpot.fr.safetynet.entity;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FireStation {
    private String address;
    @Setter
    private String station;
}
