import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 1: Parameterized Tests
 *
 * @ParameterizedTest: runs the same test method multiple times
 *                     with different inputs (avoids code duplication)
 *
 * Sources of input data:
 *   @ValueSource  : single value per run
 *   @CsvSource    : multiple values per run (like a table)
 */
public class EvenCheckerTest {

    EvenChecker evenChecker = new EvenChecker();

    // Test isEven() with multiple EVEN numbers using @ValueSource
    // Runs this test 5 times — once per value in the array
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    public void testIsEvenWithEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number),
                number + " should be even");
    }

    // Test isEven() with multiple ODD numbers using @ValueSource
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    public void testIsEvenWithOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number),
                number + " should be odd");
    }

    // Test isEven() with number AND expected result using @CsvSource
    // Each CSV row: (input, expectedResult)
    // Runs once per row - 6 test runs total
    @ParameterizedTest
    @CsvSource({
        "2,  true",
        "3,  false",
        "10, true",
        "11, false",
        "0,  true",
        "-4, true"
    })
    public void testIsEvenWithExpectedResult(int number, boolean expected) {
        assertEquals(expected, evenChecker.isEven(number),
                "isEven(" + number + ") should return " + expected);
    }
}
