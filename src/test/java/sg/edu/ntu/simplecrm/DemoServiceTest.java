package sg.edu.ntu.simplecrm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sg.edu.ntu.simplecrm.service.DemoService;

public class DemoServiceTest {

  DemoService demoService;

  @BeforeEach
  public void init() {
    demoService = new DemoService();
  }

  @Test
  public void testAdd() {
    // 1. SETUP / ARRANGE
    // DemoService demoService = new DemoService();

    // Expected result
    int expectedResult = 8;

    // 2. EXECUTE / ACT
    // Call the method that we want to test
    int actualResult = demoService.add(3, 5);

    // 3. ASSERT
    assertEquals(expectedResult, actualResult, "3+5 should be 8");

  }

  @Test
  public void testSubtract() {
    // 1. SETUP / ARRANGE
    // DemoService demoService = new DemoService();

    int expectedResult = 2;

    // 2. EXECUTE
    int actualResult = demoService.subtract(5, 3);

    // 3. ASSERT
    assertEquals(expectedResult, actualResult, "5 - 3 should be 2");
  }

  @DisplayName("Test multiply")
  @Test
  public void testMultiply() {
    // 1. SETUP / ARRANGE
    // DemoService demoService = new DemoService();

    int expectedResult = 15;

    // 2. EXECUTE
    int actualResult = demoService.multiply(5, 3);

    // 3. ASSERT
    assertEquals(expectedResult, actualResult, "5 * 3 should be 15");
  }

  @DisplayName("Test divide")
  @Test
  public void testDivide() {
    // 1. SETUP / ARRANGE
    // DemoService demoService = new DemoService();

    int expectedResult = 2;

    // 2. EXECUTE
    int actualResult = demoService.divide(10, 5);

    // 3. ASSERT
    assertEquals(expectedResult, actualResult, "10 / 5 should be 2");
  }

  @Test
  public void testIsEvenWithOddNumber() {

    // 1. SETUP
    int oddNumber = 11;

    // 2. EXECUTE
    boolean result = demoService.isEven(oddNumber);

    // 3. ASSERT
    assertFalse(result, "11 should be odd");
  }

  @Test
  public void testIsEvenWithEvenNumber() {

    // 1. SETUP
    int evenNumber = 12;

    // 2. EXECUTE
    boolean result = demoService.isEven(evenNumber);

    // 3. ASSERT
    assertTrue(result, "12 should be even");
  }

}
