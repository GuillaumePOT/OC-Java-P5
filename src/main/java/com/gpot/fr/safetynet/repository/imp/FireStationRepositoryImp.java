package com.gpot.fr.safetynet.repository.imp;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;

import com.gpot.fr.safetynet.entity.FireStation;
import com.gpot.fr.safetynet.repository.FireStationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
class FireStationRepositoryImp extends DataRepository implements FireStationRepository {

  protected static final List<FireStation> FIRE_STATION_LIST = new ArrayList<>();

  @Override
  public FireStation save(FireStation fireStation) {
    FIRE_STATION_LIST.add(fireStation);
    return fireStation;
  }

  @Override
  public void delete(String address) {
    FIRE_STATION_LIST
      .stream()
      .filter(p -> p.getAddress().equalsIgnoreCase(address))
      .findFirst()
      .ifPresentOrElse(
        fireStation -> {
          FIRE_STATION_LIST.remove(fireStation);
          log.info("Firestation at " + address + " deleted");
        },
        () -> log.error("Firestation not existing in DB and can't be removed")
      );
  }

  @Override
  public List<FireStation> findAll() {
    return FIRE_STATION_LIST;
  }

  @Override
  public List<String> findAddressByStationNumber(String stationNumber) {
    return FIRE_STATION_LIST
      .stream()
      .filter(p -> p.getStation().equalsIgnoreCase(stationNumber))
      .map(FireStation::getAddress)
      .toList();
  }

  @Override
  public String findStationNumberByAddress(String address) {
    return FIRE_STATION_LIST
      .stream()
      .filter(f -> f.getAddress().equalsIgnoreCase(address))
      .findFirst()
      .map(FireStation::getStation)
      .orElse(null);
  }

  @Override
  public FireStation update(FireStation fireStation) {
    FIRE_STATION_LIST
      .stream()
      .filter(p -> p.getAddress().equalsIgnoreCase(fireStation.getAddress()))
      .findFirst()
      .ifPresentOrElse(
        stationFound -> {
          stationFound.setStation(fireStation.getStation());
          log.info("Existing Firestation updated " + asJson(fireStation));
        },
        () -> log.error("FireStation not existing in DB and hasn't been updated")
      );
    return fireStation;
  }
}
