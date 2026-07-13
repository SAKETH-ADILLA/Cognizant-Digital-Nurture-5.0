// ===== PerformanceTester.java =====
/**
 * Exercise 5: Timeout and Performance Testing
 *
 * Class with methods that have varying execution times.
 * Place in src/main/java/
 */
public class PerformanceTester {

    // Fast task - completes in ~50ms (should pass timeout test)
    public String performTask() {
        try {
            Thread.sleep(50); // Simulate fast processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Task completed";
    }

    // Slow task - completes in ~3000ms (should fail timeout test)
    public String performSlowTask() {
        try {
            Thread.sleep(3000); // Simulate slow processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Slow task completed";
    }

    // Sorting task - performance test on large data
    public void sortLargeArray(int[] arr) {
        java.util.Arrays.sort(arr);
    }
}


// ===== PerformanceTesterTest.java =====
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 5: Timeout and Performance Testing
 *
 * JUnit 5 provides two timeout methods:
 *
 * assertTimeout(Duration, Executable)
 *   - Runs the code, waits for it to finish, THEN checks if it exceeded timeout
 *   - Does NOT abort execution when time limit is exceeded
 *
 * assertTimeoutPreemptively(Duration, Executable)
 *   - Runs the code in a SEPARATE thread
 *   - ABORTS execution immediately when time limit is exceeded
 *   - Use when the method could run forever (infinite loop, deadlock)
 */
public class PerformanceTesterTest {

    PerformanceTester tester = new PerformanceTester();

    // Test that performTask() completes within 1 second
    @Test
    public void testPerformTaskCompletesWithinTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            String result = tester.performTask();
            assertEquals("Task completed", result);
        });
    }

    // Test using assertTimeoutPreemptively - aborts if exceeded
    // performTask() takes ~50ms so this should PASS easily
    @Test
    public void testPerformTaskPreemptively() {
        String result = assertTimeoutPreemptively(
                Duration.ofMillis(500),
                () -> tester.performTask()
        );
        assertEquals("Task completed", result);
    }

    // Test that slow task FAILS to complete within 1 second
    // assertThrows wrapping assertTimeoutPreemptively captures the failure
    @Test
    public void testSlowTaskExceedsTimeout() {
        // We EXPECT this to time out - so we assert that assertTimeoutPreemptively throws
        assertThrows(org.opentest4j.AssertionFailedError.class, () ->
                assertTimeoutPreemptively(
                        Duration.ofSeconds(1),
                        () -> tester.performSlowTask()
                )
        );
    }

    // Performance test: sorting 100,000 elements should complete within 2 seconds
    @Test
    public void testSortLargeArrayPerformance() {
        int[] largeArray = new int[100_000];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = random.nextInt();
        }

        assertTimeout(Duration.ofSeconds(2), () -> {
            tester.sortLargeArray(largeArray);
        }, "Sorting 100,000 elements should complete within 2 seconds");
    }

    // Test with very tight timeout (milliseconds level)
    @Test
    public void testMillisecondTimeout() {
        assertTimeout(Duration.ofMillis(200), () -> {
            String result = tester.performTask(); // ~50ms
            assertNotNull(result);
        });
    }
}
