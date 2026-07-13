import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 *             Setup and Teardown Methods in JUnit
 *
 * AAA Pattern:
 *   Arrange : Set up the test data and conditions
 *   Act     : Execute the method being tested
 *   Assert  : Verify the result is as expected
 *
 * @Before : runs BEFORE each test method (setup)
 * @After  : runs AFTER each test method  (teardown)
 */
public class BankAccountTest {

    // Test fixture: shared object used across all tests
    private BankAccount account;

    // @Before: setup method - runs before EACH test
    // Use to initialize test fixtures so each test starts with a clean state
    @Before
    public void setUp() {
        System.out.println("Setting up test fixture...");
        // Arrange: create a fresh BankAccount before each test
        account = new BankAccount("John", 1000.0);
    }

    // @After: teardown method - runs after EACH test
    // Use to clean up resources (close DB connections, delete temp files, etc.)
    @After
    public void tearDown() {
        System.out.println("Tearing down test fixture...");
        account = null;
    }

    // Test deposit using AAA pattern
    @Test
    public void testDeposit() {
        // Arrange: account created in @Before with balance 1000.0
        double depositAmount = 500.0;

        // Act: perform the action being tested
        account.deposit(depositAmount);

        // Assert: verify the expected result
        assertEquals(1500.0, account.getBalance(), 0.001);
    }

    // Test withdrawal using AAA pattern
    @Test
    public void testWithdraw() {
        // Arrange
        double withdrawAmount = 200.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(800.0, account.getBalance(), 0.001);
    }

    // Test that balance remains unchanged when deposit is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        // Arrange
        double negativeAmount = -100.0;

        // Act + Assert: exception expected
        account.deposit(negativeAmount);
    }

    // Test that withdraw throws exception for insufficient funds
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double excessiveAmount = 2000.0; // more than balance of 1000

        // Act + Assert: exception expected
        account.withdraw(excessiveAmount);
    }

    // Test initial balance is correctly set
    @Test
    public void testInitialBalance() {
        // Arrange: done in @Before

        // Act: get the balance (no state change)
        double balance = account.getBalance();

        // Assert
        assertEquals(1000.0, balance, 0.001);
    }

    // Test account owner name
    @Test
    public void testOwnerName() {
        // Arrange: done in @Before

        // Act
        String owner = account.getOwner();

        // Assert
        assertEquals("John", owner);
        assertNotNull(owner);
    }

    // Test multiple operations in sequence
    @Test
    public void testMultipleTransactions() {
        // Arrange
        double depositAmount  = 500.0;
        double withdrawAmount = 300.0;

        // Act
        account.deposit(depositAmount);
        account.withdraw(withdrawAmount);

        // Assert: 1000 + 500 - 300 = 1200
        assertEquals(1200.0, account.getBalance(), 0.001);
    }
}
