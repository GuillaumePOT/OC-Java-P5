package com.gpot.fr.safetynet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpot.fr.safetynet.entity.MedicalRecords;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

  @Getter
  @Setter
  private static int minorCount = 0;

  @Getter
  @Setter
  private static int majorCount = 0;

  @Getter
  private static final ObjectMapper mapper = new ObjectMapper();

  public static boolean isThereMinor(MedicalRecords records) {
    return calculateAge(records) <= 18;
  }

  public static int calculateAge(MedicalRecords records) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate localDate = LocalDate.parse(records.getBirthdate(), formatter);
    Period age = Period.between(localDate, LocalDate.now());
    return age.getYears();
  }

  public static String asJson(final Object obj) throws JsonProcessingException {
    return mapper.writeValueAsString(obj);
  }
}
