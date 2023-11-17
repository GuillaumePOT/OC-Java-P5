package com.gpot.fr.safetynet.repository;

import com.gpot.fr.safetynet.entity.Person;
import java.util.List;

public interface PersonRepository {
  /**
   * Save Person
   * @param person Person
   * @return Person
   */
  Person save(Person person);

  /**
   * Delete Person by first and last Name
   * @param firstName String
   * @param lastName String
   */
  void delete(String firstName, String lastName);

  /**
   * Find all Persons
   * @return List<Person>
   */
  List<Person> findAll();

  /**
   * Update Person
   * @param person Person
   * @return Person
   */
  Person update(Person person);

  /**
   * Find email by city
   * @param city String
   * @return  List<String>
   */
  List<String> findEmailByCity(String city);

  /**
   * Find Person By Address List
   * @param addressList List<String>
   * @return List<Person>
   */
  List<Person> findPersonByAddressList(List<String> addressList);

  /**
   * Find Person by Address
   * @param address String
   * @return a list of Person
   */
  List<Person> findPersonByAddress(String address);

  /**
   * Find phone by Station
   * @param addressList List<String>
   * @return List<String>
   */
  List<String> findPhoneByStation(List<String> addressList);

  /**
   * Find person by first and last name
   * @param firstName String
   * @param lastName String
   * @return List<Person>
   */
  List<Person> findPersonByFirstAndLastName(String firstName, String lastName);
}
