package com.gpot.fr.safetynet.repository.imp;

import static com.gpot.fr.safetynet.repository.imp.FireStationRepositoryImp.FIRE_STATION_LIST;
import static com.gpot.fr.safetynet.repository.imp.MedicalRecordsRepositoryImp.MEDICAL_RECORDS_LIST;
import static com.gpot.fr.safetynet.repository.imp.PersonRepositoryImp.PERSON_LIST;

import com.gpot.fr.safetynet.entity.Data;
import com.gpot.fr.safetynet.utils.AppUtils;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class DataRepository {

  public static void init() throws IOException {
    Data data = AppUtils.getMapper().readValue(new File("src/main/resources/Data.json"), Data.class);
    PERSON_LIST.clear();
    PERSON_LIST.addAll(data.getPersons());
    FIRE_STATION_LIST.clear();
    FIRE_STATION_LIST.addAll(data.getFirestations());
    MEDICAL_RECORDS_LIST.clear();
    MEDICAL_RECORDS_LIST.addAll(data.getMedicalrecords());
  }
}
