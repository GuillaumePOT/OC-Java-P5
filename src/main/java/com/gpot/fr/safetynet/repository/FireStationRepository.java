package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.FireStation;
import java.util.List;

public interface FireStationRepository {
  /**
   * Save Firestation
   * @param fireStation FireStation
   * @return Firestation
   */
  FireStation save(FireStation fireStation);

  /**
   * Delete Firestation by address
   * @param address String
   */
  void delete(String address);

  /**
   * Update Firestation
   * @param fireStation FireStation
   * @return FireStation
   */
  FireStation update(FireStation fireStation);

  /**
   * Find all firestations
   * @return  List<FireStation>
   */
  List<FireStation> findAll();

  /**
   * Find list of address by station number
   * @param stationNumber String
   * @return List<String>
   */
  List<String> findAddressByStationNumber(String stationNumber);

  /**
   * Find station number by address
   * @param address String
   * @return String
   */
  String findStationNumberByAddress(String address);
}
