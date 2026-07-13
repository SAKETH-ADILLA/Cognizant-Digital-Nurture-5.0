import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 1: Setting Up JUnit
 *
 * Steps done:
 * 1. Created a Java project
 * 2. Added JUnit 4.13.2 dependency to pom.xml (see pom.xml)
 * 3. Created this test class
 *
 * To run: right-click -> Run As -> JUnit Test in IDE
 *         OR: mvn test from terminal
 */
public class MyFirstTest {

    // @Test marks this method as a test case
    // JUnit will automatically discover and run all @Test methods
    @Test
    public void myFirstJUnitTest() {
        // Simple test to verify JUnit is set up correctly
        System.out.println("JUnit is set up and working!");
        assertTrue("JUnit setup is working", true);
    }
}
