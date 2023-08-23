package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.FireStation;
import java.util.List;
public interface FireStationRepository {
    FireStation save(FireStation fireStation);
    void delete(String address);
    FireStation update(FireStation fireStation);
    List<FireStation> findAll();
}
