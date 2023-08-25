package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import java.util.List;
public interface FireStationService {
    FireStation save(FirestationDto fireStation);
    void delete(String address);
    FireStation update(FirestationDto fireStation);
    List<FireStation> findAll();
}
