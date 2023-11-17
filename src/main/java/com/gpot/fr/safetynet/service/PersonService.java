package com.gpot.fr.safetynet.service;

import com.gpot.fr.safetynet.dto.PersonDto;
import com.gpot.fr.safetynet.entity.Person;
import java.util.List;

public interface PersonService {
  /**
  Save Person
   @param dto PersonDto
   @return Person
   */
  Person save(PersonDto dto);

  /**
   Delete Person by first and last name
   @param firstName String
   @param lastName String
   */
  void delete(String firstName, String lastName);

  /**
   * Find all persons
   * @return List<Person>
   */
  List<Person> findAll();

  /**
   * Update Person
   * @param dto PersonDto
   * @return Person
   */
  Person update(PersonDto dto);

  /**
   * Find email by city
   * @param city String
   * @return List<String>
   */
  List<String> findEmailByCity(String city);

  /**
   * Find person by a list of address
   * @param addressList List<String>
   * @return List<Person>
   */
  List<Person> findPersonByAddressList(List<String> addressList);

  /**
   *  Find person by address
   * @param address String
   * @return List<Person>
   */
  List<Person> findPersonByAddress(String address);

  /**
   * Find phone by a list of station
   * @param addressList List<String>
   * @return List<String>
   */
  List<String> findPhoneByStationList(List<String> addressList);

  /**
   * Find person by first and last name
   * @param firstName String
   * @param lastName String
   * @return List<Person>
   */
  List<Person> findPersonsByFirstAndLastName(String firstName, String lastName);
}
