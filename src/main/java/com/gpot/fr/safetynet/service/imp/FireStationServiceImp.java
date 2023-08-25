package com.gpot.fr.safetynet.service.imp;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import com.gpot.fr.safetynet.repository.FireStationRepository;
import com.gpot.fr.safetynet.service.FireStationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FireStationServiceImp implements FireStationService {
    private final FireStationRepository fireStationRepository;
    @Override
    public FireStation save(FirestationDto dto) {
        final var fireStation = FireStation.builder()
                .address(dto.getAddress())
                .station(dto.getStation())
                .build();
        return fireStationRepository.save(fireStation);
    }
    @Override
    public void delete(String address) {
        fireStationRepository.delete(address);
    }
    @Override
    public FireStation update(FirestationDto dto) {
        final var fireStation = FireStation.builder()
                .address(dto.getAddress())
                .station(dto.getStation())
                .build();
        return fireStationRepository.update(fireStation);
    }
    @Override
    public List<FireStation> findAll() {
        return fireStationRepository.findAll();
    }
}
