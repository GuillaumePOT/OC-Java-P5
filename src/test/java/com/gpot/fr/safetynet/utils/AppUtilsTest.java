package com.gpot.fr.safetynet.utils;

import static com.gpot.fr.safetynet.utils.AppUtils.calculateAge;
import static com.gpot.fr.safetynet.utils.AppUtils.isThereMinor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gpot.fr.safetynet.entity.MedicalRecords;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class AppUtilsTest {

  private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
  private final String majorDate = LocalDate.now().minusYears(20).format(pattern);
  private final String minorDate = LocalDate.now().minusYears(16).format(pattern);

  @Test
  void itShouldIsThereMinor() {
    final var medicalRecordsMinor = MedicalRecords.builder().birthdate(minorDate).build();
    assertTrue(isThereMinor(medicalRecordsMinor));
    final var medicalRecordsMajor = MedicalRecords.builder().birthdate(majorDate).build();
    assertFalse(isThereMinor(medicalRecordsMajor));
  }

  @Test
  void itShouldCalculateAge() {
    final var medicalRecords = MedicalRecords.builder().birthdate(majorDate).build();
    final var result = calculateAge(medicalRecords);
    assertEquals(20, result);
  }
}
