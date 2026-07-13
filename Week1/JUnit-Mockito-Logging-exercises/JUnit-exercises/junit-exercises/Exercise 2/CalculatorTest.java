import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 2: Writing Basic JUnit Tests
 *
 * JUnit test class for Calculator.
 * Place this in src/test/java/
 *
 * Each @Test method is an independent test case.
 * Test method names should clearly describe what is being tested.
 */
public class CalculatorTest {

    // Create one Calculator instance for all tests
    Calculator calculator = new Calculator();

    // Test add()
    @Test
    public void testAdd() {
        int result = calculator.add(3, 4);
        assertEquals(7, result);
    }

    // Test subtract()
    @Test
    public void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    // Test multiply()
    @Test
    public void testMultiply() {
        int result = calculator.multiply(3, 5);
        assertEquals(15, result);
    }

    // Test divide()
    @Test
    public void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001); // delta for floating point comparison
    }

    // Test divide by zero throws exception
    // @Test(expected = ...) passes only if the expected exception is thrown
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }

    // Test isEven() with even number
    @Test
    public void testIsEvenWithEvenNumber() {
        assertTrue(calculator.isEven(4));
    }

    // Test isEven() with odd number
    @Test
    public void testIsEvenWithOddNumber() {
        assertFalse(calculator.isEven(5));
    }

    // Test max()
    @Test
    public void testMax() {
        int result = calculator.max(10, 20);
        assertEquals(20, result);
    }
}
