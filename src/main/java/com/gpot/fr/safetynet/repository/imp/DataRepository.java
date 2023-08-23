package com.gpot.fr.safetynet.repository.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpot.fr.safetynet.entity.Data;

import java.io.File;
import java.io.IOException;

import static com.gpot.fr.safetynet.repository.imp.FireStationRepositoryImp.FIRE_STATION_LIST;
import static com.gpot.fr.safetynet.repository.imp.PersonRepositoryImp.PERSON_LIST;

public abstract class DataRepository {
    public static void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Data data = objectMapper.readValue(new File("src/main/resources/Data.json"),Data.class);
        PERSON_LIST.addAll(data.getPersons());
        FIRE_STATION_LIST.addAll(data.getFirestations());

    }
}
