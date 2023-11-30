package com.gpot.fr.safetynet.repository.imp;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;

import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class PersonRepositoryImp extends DataRepository implements PersonRepository {

  protected static final List<Person> PERSON_LIST = new ArrayList<>();

  @Override
  public Person save(Person person) {
    PERSON_LIST.add(person);
    return person;
  }

  @Override
  public void delete(String firstName, String lastName) {
    PERSON_LIST
      .stream()
      .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
      .findFirst()
      .ifPresentOrElse(
        person -> {
          PERSON_LIST.remove(person);
          log.info("Person " + lastName + " " + firstName + " deleted");
        },
        () -> log.error("Person not existing in DB and can't be removed")
      );
  }

  @Override
  public List<Person> findAll() {
    return PERSON_LIST;
  }

  @Override
  public Person update(Person person) {
    PERSON_LIST
      .stream()
      .filter(p ->
        p.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
        p.getLastName().equalsIgnoreCase(person.getLastName())
      )
      .findFirst()
      .ifPresentOrElse(
        personFound -> {
          personFound.setAddress(person.getAddress());
          personFound.setCity(person.getCity());
          personFound.setZip(person.getZip());
          personFound.setPhone(person.getPhone());
          personFound.setEmail(person.getEmail());
          log.info("Existing Person updated : " + asJson(person));
        },
        () -> log.error("Person not existing in DB and hasn't been updated")
      );

    return person;
  }

  @Override
  public List<String> findEmailByCity(String city) {
    return PERSON_LIST.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).map(Person::getEmail).toList();
  }

  @Override
  public List<Person> findPersonByAddressList(List<String> addressList) {
    List<Person> personList = new ArrayList<>();
    for (String address : addressList) {
      personList.addAll(findPersonByAddress(address));
    }
    return personList;
  }

  @Override
  public List<Person> findPersonByAddress(String address) {
    List<Person> personList = new ArrayList<>();
    PERSON_LIST.stream().filter(p -> p.getAddress().equalsIgnoreCase(address)).forEach(personList::add);
    return personList;
  }

  @Override
  public List<String> findPhoneByStation(List<String> addressList) {
    List<String> phoneList = new ArrayList<>();
    addressList.forEach(address ->
      PERSON_LIST
        .stream()
        .filter(p -> p.getAddress().equalsIgnoreCase(address))
        .forEach(person -> phoneList.add(person.getPhone()))
    );
    return phoneList;
  }

  @Override
  public List<Person> findPersonByFirstAndLastName(String firstName, String lastName) {
    List<Person> personList = new ArrayList<>();
    PERSON_LIST
      .stream()
      .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
      .forEach(personList::add);
    return personList;
  }
}
