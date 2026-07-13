import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 3: Assertions in JUnit
 *
 * Demonstrates all commonly used JUnit assertion methods.
 */
public class AssertionsTest {

    @Test
    public void testAssertions() {

        // assertEquals: checks that two values are equal
        assertEquals(5, 2 + 3);
        assertEquals("Hello", "Hel" + "lo");
        assertEquals(5.0, 4.5 + 0.5, 0.001); // delta for floating point

        // assertTrue: checks that condition is true
        assertTrue(5 > 3);
        assertTrue("List should not be empty", !new java.util.ArrayList<>().add(null) || true);

        // assertFalse: checks that condition is false
        assertFalse(5 < 3);
        assertFalse("String should not be empty", "hello".isEmpty());

        // assertNull: checks that object is null
        assertNull(null);
        String str = null;
        assertNull("String should be null", str);

        // assertNotNull: checks that object is NOT null
        assertNotNull(new Object());
        assertNotNull("String should not be null", "hello");

        // assertSame: checks that two references point to the SAME object
        String s1 = "same";
        String s2 = s1;
        assertSame(s1, s2);

        // assertNotSame: checks that two references point to DIFFERENT objects
        String s3 = new String("different");
        String s4 = new String("different");
        assertNotSame(s3, s4);

        // assertArrayEquals: checks that two arrays are equal
        int[] expected = {1, 2, 3};
        int[] actual   = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    // Test that demonstrates fail() - forces test to fail with a message
    // Useful as a placeholder for tests not yet implemented
    // @Test
    // public void testNotYetImplemented() {
    //     fail("This test is not yet implemented");
    // }

    @Test
    public void testEqualsWithMessage() {
        // Assertions can include a failure message as the first argument
        int expected = 10;
        int actual   = 5 + 5;
        assertEquals("5 + 5 should equal 10", expected, actual);
    }

    @Test
    public void testStringAssertions() {
        String result = "Hello World";
        assertNotNull(result);
        assertTrue(result.startsWith("Hello"));
        assertTrue(result.contains("World"));
        assertEquals(11, result.length());
    }
}
