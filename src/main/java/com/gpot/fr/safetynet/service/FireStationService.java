package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.FirestationDto;
import com.gpot.fr.safetynet.entity.FireStation;
import java.util.List;

public interface FireStationService {
  /**
   * Save Firestationdto
   * @param fireStation FirestationDto
   * @return Firestation
   */
  FireStation save(FirestationDto fireStation);

  /**
   * Delete Firestation by address
   * @param address String
   */
  void delete(String address);

  /**
   * Update Firestationdto
   * @param fireStation FirestationDto
   * @return Firestation

   */
  FireStation update(FirestationDto fireStation);

  /**
   * find all Firestation
   * @return List<Firestation>
   */
  List<FireStation> findAll();

  /**
   * find address by station number
   * @param stationNumber String
   * @return List<String>
   */
  List<String> findAddressByStationNumber(String stationNumber);

  /**
   * find station number by address
   * @param address String
   * @return String
   */
  String findStationNumberByAddress(String address);
}
