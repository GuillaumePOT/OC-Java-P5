package com.gpot.fr.safetynet.controller;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.service.FireStationService;
import com.gpot.fr.safetynet.service.MedicalRecordsService;
import com.gpot.fr.safetynet.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class AlertController {
    private final MedicalRecordsService medicalRecordsService;
    private final FireStationService fireStationService;
    private final PersonService personService;

    @GetMapping("/firestation")
    public ResponseEntity<List<Person>> findPersonsCoveredByStation(@RequestParam ( name = "stationNumber") String stationNumber){
        List<String> addressList = fireStationService.findAddressByStationNumber(stationNumber);
        List<Person> personList = personService.findPersonByAddress(addressList);
        return new ResponseEntity<>(personList,HttpStatus.OK);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> findPhoneByStation(@RequestParam (name = "firestation")String stationNumber){
        List<String> addressList = fireStationService.findAddressByStationNumber(stationNumber);
        List<String> phoneList = personService.findPhoneByStation(addressList);
        return  new ResponseEntity<>(phoneList,HttpStatus.OK);
    }

/*    @GetMapping("/flood/station")
    public ResponseEntity<> findHomeByStationList(@RequestParam (name = "station")List<String> stationList){
        List<String> addressList = new ArrayList<>();
        for(String stationNumber:stationList){
            addressList.addAll(fireStationService.findAddressByStationNumber(stationNumber));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
*/
}
