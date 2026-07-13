// ===== ExceptionThrower.java =====
/**
 * Exercise 4: Exception Testing
 *
 * Class with methods that throw various exceptions.
 * Place in src/main/java/
 */
public class ExceptionThrower {

    // Throws IllegalArgumentException for negative input
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    // Throws IllegalArgumentException for null or empty input
    public String processName(String name) {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name.toUpperCase();
    }

    // Throws custom exception
    public void throwException(String message) {
        throw new RuntimeException(message);
    }

    // Throws IndexOutOfBoundsException
    public int getElement(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return arr[index];
    }
}


// ===== ExceptionThrowerTest.java =====
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 4: Exception Testing
 *
 * assertThrows(ExceptionType.class, () -> { code that should throw })
 *   - Passes if the expected exception IS thrown
 *   - Fails if the exception is NOT thrown
 *   - Returns the exception object so you can verify the message
 *
 * JUnit 5 replaces @Test(expected=...) from JUnit 4
 * with the more powerful assertThrows()
 */
public class ExceptionThrowerTest {

    ExceptionThrower thrower = new ExceptionThrower();

    // Test that divide by zero throws ArithmeticException
    @Test
    public void testDivideByZeroThrowsException() {
        // assertThrows returns the thrown exception
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> thrower.divide(10, 0)
        );
        // Verify exception message
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    // Test that null name throws NullPointerException
    @Test
    public void testNullNameThrowsException() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> thrower.processName(null)
        );
        assertEquals("Name cannot be null", exception.getMessage());
    }

    // Test that empty name throws IllegalArgumentException
    @Test
    public void testEmptyNameThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> thrower.processName("")
        );
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    // Test throwException() throws RuntimeException with correct message
    @Test
    public void testThrowExceptionWithMessage() {
        String expectedMessage = "Test exception message";
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> thrower.throwException(expectedMessage)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    // Test out of bounds index throws ArrayIndexOutOfBoundsException
    @Test
    public void testArrayOutOfBounds() {
        int[] arr = {1, 2, 3};
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> thrower.getElement(arr, 10)
        );
    }

    // Test valid divide does NOT throw exception
    @Test
    public void testValidDivideDoesNotThrow() {
        assertDoesNotThrow(() -> thrower.divide(10, 2));
        assertEquals(5, thrower.divide(10, 2));
    }
}
