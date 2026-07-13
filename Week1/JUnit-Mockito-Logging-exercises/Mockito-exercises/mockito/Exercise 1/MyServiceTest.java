import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 1: Mocking and Stubbing
 *
 * mock()  : creates a fake implementation of the interface/class
 *           all methods return default values (null, 0, false) by default
 *
 * when(mock.method()).thenReturn(value)
 *         : stubs the method to return a specific value when called
 *           this is called "stubbing"
 *
 * Why mock?
 *   - External APIs are slow, expensive, or unavailable in test environments
 *   - We want to test OUR code, not the external API
 */
public class MyServiceTest {

    @Test
    public void testExternalApi() {
        // Step 1: Create mock object for ExternalApi
        // Mockito creates a fake ExternalApi that doesn't do anything real
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Step 2: Stub the method to return predefined value
        // "When getData() is called on mockApi, return 'Mock Data'"
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Inject mock into the service and call the method
        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        // Step 4: Assert the result matches the stubbed return value
        assertEquals("Mock Data", result);
    }

    @Test
    public void testFetchUserData() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub getUserData() for a specific user ID
        when(mockApi.getUserData("user123")).thenReturn("John Doe");

        MyService service = new MyService(mockApi);
        String result = service.fetchUserData("user123");

        assertEquals("John Doe", result);
    }

    @Test
    public void testFetchDataReturnsNull() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // By default (no stub) mock returns null for object methods
        // Explicitly stubbing null:
        when(mockApi.getData()).thenReturn(null);

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertNull(result);
    }
}
