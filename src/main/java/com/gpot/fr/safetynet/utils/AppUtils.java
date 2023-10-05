package com.gpot.fr.safetynet.utils;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

public class AppUtils {

  @Getter
  @Setter
  private static int minorCount = 0;

  @Getter
  @Setter
  private static int majorCount = 0;

  public static boolean isThereMinor(MedicalRecords records) {
    return calculateAge(records) <= 18;
  }

  public static int calculateAge(MedicalRecords records) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate localDate = LocalDate.parse(records.getBirthdate(), formatter);
    Period age = Period.between(localDate, LocalDate.now());
    return age.getYears();
  }
}
