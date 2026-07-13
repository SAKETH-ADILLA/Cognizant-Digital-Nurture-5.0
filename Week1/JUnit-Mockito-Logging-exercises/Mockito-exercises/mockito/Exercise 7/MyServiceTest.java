import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 7: Handling Void Methods with Exceptions
 *
 * For void methods that need to throw exceptions, use:
 *
 * doThrow(new ExceptionType()).when(mock).voidMethod()
 *   : stubs the void method to throw the given exception when called
 *
 * doThrow(new ExceptionType("message")).when(mock).voidMethod(arg)
 *   : throws exception only when called with specific argument
 *
 * Why test exception throwing?
 *   - Ensure your service handles failures from dependencies correctly
 *   - Verify error handling / recovery logic
 */
public class MyServiceTest {

    @Test
    public void testVoidMethodThrowsException() {
        // Arrange
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub postData() to throw RuntimeException when called
        doThrow(new RuntimeException("API unavailable"))
                .when(mockApi).postData(anyString());

        MyService service = new MyService(mockApi);

        // Act + Assert: verify the exception propagates from the void method
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.submitData("some data")
        );

        assertEquals("API unavailable", exception.getMessage());

        // Verify postData was still called (even though it threw)
        verify(mockApi).postData("some data");
    }

    @Test
    public void testNotificationThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub sendNotification() to throw IllegalStateException
        doThrow(new IllegalStateException("Notification service down"))
                .when(mockApi).sendNotification(anyString());

        MyService service = new MyService(mockApi);

        assertThrows(
                IllegalStateException.class,
                () -> service.notifyUser("Test Message")
        );

        verify(mockApi).sendNotification("Test Message");
    }

    @Test
    public void testVoidThrowsOnSpecificArgument() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Throw exception ONLY for specific argument "INVALID"
        doThrow(new IllegalArgumentException("Invalid data"))
                .when(mockApi).postData("INVALID");

        // doNothing for any other argument
        doNothing().when(mockApi).postData(not(eq("INVALID")));

        MyService service = new MyService(mockApi);

        // Valid data - should NOT throw
        assertDoesNotThrow(() -> service.submitData("valid data"));

        // Invalid data - SHOULD throw
        assertThrows(
                IllegalArgumentException.class,
                () -> service.submitData("INVALID")
        );
    }

    @Test
    public void testVoidThrowsThenSucceeds() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // First call throws, second call succeeds (retry scenario)
        doThrow(new RuntimeException("Temporary failure"))
                .doNothing()
                .when(mockApi).postData(anyString());

        // First call throws
        assertThrows(RuntimeException.class, () -> mockApi.postData("data"));

        // Second call succeeds
        assertDoesNotThrow(() -> mockApi.postData("data"));

        // Verify postData was called exactly twice
        verify(mockApi, times(2)).postData("data");
    }
}
