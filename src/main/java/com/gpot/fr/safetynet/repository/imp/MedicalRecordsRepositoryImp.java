package com.gpot.fr.safetynet.repository.imp;

import static com.gpot.fr.safetynet.utils.AppUtils.asJson;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import com.gpot.fr.safetynet.entity.Person;
import com.gpot.fr.safetynet.repository.MedicalRecordsRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MedicalRecordsRepositoryImp extends DataRepository implements MedicalRecordsRepository {

  protected static final List<MedicalRecords> MEDICAL_RECORDS_LIST = new ArrayList<>();

  @Override
  public MedicalRecords save(MedicalRecords medicalRecords) {
    MEDICAL_RECORDS_LIST.add(medicalRecords);
    return medicalRecords;
  }

  @Override
  public void delete(String firstName, String lastName) {
    MEDICAL_RECORDS_LIST.remove(
      MEDICAL_RECORDS_LIST
        .stream()
        .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
        .findFirst()
        .map(medicalRecords -> {
          log.info("MedicalRecords of " + lastName + " " + firstName + " deleted");
          return medicalRecords;
        })
        .orElse(medicalRecordsNotFound())
    );
  }

  private MedicalRecords medicalRecordsNotFound() {
    log.error("MedicalRecords not existing in DB and can't be removed");
    return null;
  }

  @Override
  public List<MedicalRecords> findRecordsByPersonList(List<Person> personList) {
    List<MedicalRecords> recordList = new ArrayList<>();
    personList.forEach(person ->
      MEDICAL_RECORDS_LIST
        .stream()
        .filter(p ->
          p.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
          p.getLastName().equalsIgnoreCase(person.getLastName())
        )
        .forEach(recordList::add)
    );
    return recordList;
  }

  @Override
  public List<MedicalRecords> findAll() {
    return MEDICAL_RECORDS_LIST;
  }

  @Override
  public MedicalRecords update(MedicalRecords medicalRecords) {
    MEDICAL_RECORDS_LIST
      .stream()
      .filter(p ->
        p.getFirstName().equalsIgnoreCase(medicalRecords.getFirstName()) &&
        p.getLastName().equalsIgnoreCase(medicalRecords.getLastName())
      )
      .findFirst()
      .ifPresentOrElse(
        medicalRecordsFound -> {
          medicalRecordsFound.setAllergies(medicalRecords.getAllergies());
          medicalRecordsFound.setBirthdate(medicalRecords.getBirthdate());
          medicalRecordsFound.setMedications(medicalRecords.getMedications());
          log.info("Existing MedicalRecords updated : " + asJson(medicalRecords));
        },
        () -> log.error("MedicalRecords not existing in DB and hasn't been updated")
      );
    return medicalRecords;
  }
}
