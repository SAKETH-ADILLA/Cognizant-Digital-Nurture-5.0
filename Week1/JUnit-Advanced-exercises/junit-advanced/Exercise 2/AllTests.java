// ===== MathTest.java =====
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 2: Test Suites - First test class included in the suite
 */
public class MathTest {

    @Test
    public void testAddition() {
        assertEquals(10, 4 + 6);
    }

    @Test
    public void testMultiplication() {
        assertEquals(20, 4 * 5);
    }
}


// ===== StringTest.java =====
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 2: Test Suites - Second test class included in the suite
 */
public class StringTest {

    @Test
    public void testStringLength() {
        assertEquals(5, "Hello".length());
    }

    @Test
    public void testStringUpperCase() {
        assertEquals("HELLO", "hello".toUpperCase());
    }

    @Test
    public void testStringContains() {
        assertTrue("Hello World".contains("World"));
    }
}


// ===== AllTests.java =====
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Exercise 2: Test Suites and Categories
 *
 * @Suite         : marks this class as a test suite
 * @SelectClasses : specifies which test classes to include in the suite
 *
 * Running this class runs ALL tests in MathTest AND StringTest together.
 * Useful for grouping related tests (e.g., all unit tests, all integration tests).
 *
 * Run: right-click AllTests -> Run As -> JUnit Test
 *      OR: mvn test
 */
@Suite
@SelectClasses({MathTest.class, StringTest.class})
public class AllTests {
    // No code needed — @Suite annotation handles everything
    // JUnit discovers and runs tests from the listed classes
}
