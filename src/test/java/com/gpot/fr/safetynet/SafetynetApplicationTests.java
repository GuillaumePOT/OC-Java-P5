package com.gpot.fr.safetynet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafetynetApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> SafetynetApplication.main(new String[] {}));
  }
}
