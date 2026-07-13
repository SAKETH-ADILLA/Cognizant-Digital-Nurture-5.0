import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Test Execution Order
 *
 * By default JUnit does NOT guarantee the order of test execution.
 *
 * @TestMethodOrder: specifies the ordering strategy
 *   MethodOrderer.OrderAnnotation.class -> use @Order values
 *   MethodOrderer.Alphanumeric.class    -> alphabetical by method name
 *   MethodOrderer.Random.class          -> random order each run
 *
 * @Order(n): defines execution order; lower number runs first
 *
 * Use case: integration tests where step 1 must run before step 2
 * (e.g., create user -> place order -> cancel order)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    // Simulates a shared state across ordered tests (e.g., a user session)
    private static String sharedData = "";

    // Runs FIRST
    @Test
    @Order(1)
    public void testCreateUser() {
        System.out.println("Step 1: Creating user");
        sharedData = "UserCreated";
        assertEquals("UserCreated", sharedData);
    }

    // Runs SECOND
    @Test
    @Order(2)
    public void testLogin() {
        System.out.println("Step 2: Logging in");
        sharedData = sharedData + "_LoggedIn";
        assertTrue(sharedData.contains("LoggedIn"));
    }

    // Runs THIRD
    @Test
    @Order(3)
    public void testPlaceOrder() {
        System.out.println("Step 3: Placing order");
        sharedData = sharedData + "_OrderPlaced";
        assertTrue(sharedData.contains("OrderPlaced"));
    }

    // Runs LAST
    @Test
    @Order(4)
    public void testLogout() {
        System.out.println("Step 4: Logging out");
        sharedData = sharedData + "_LoggedOut";
        assertTrue(sharedData.contains("LoggedOut"));
        System.out.println("Final state: " + sharedData);
    }
}
