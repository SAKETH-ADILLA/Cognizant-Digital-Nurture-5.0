import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * Exercise 2: Verifying Interactions
 *
 * verify(mock).method()
 *   : verifies that the method was called exactly ONCE on the mock
 *
 * verify(mock, times(n)).method()
 *   : verifies the method was called exactly n times
 *
 * verify(mock, never()).method()
 *   : verifies the method was NEVER called
 *
 * verify(mock, atLeast(n)).method()
 *   : verifies the method was called at least n times
 *
 * verify(mock, atMost(n)).method()
 *   : verifies the method was called at most n times
 *
 * Why verify?
 *   - Ensures our service actually calls the dependency correctly
 *   - Catches bugs where the method is never called or called too many times
 */
public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {
        // Arrange
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("some data");

        MyService service = new MyService(mockApi);

        // Act
        service.fetchData();

        // Verify: getData() was called exactly once
        verify(mockApi).getData();
    }

    @Test
    public void testVerifyCalledTwice() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("data");
        MyService service = new MyService(mockApi);

        // Call fetchData twice
        service.fetchData();
        service.fetchData();

        // Verify getData() was called exactly 2 times
        verify(mockApi, times(2)).getData();
    }

    @Test
    public void testVerifyNeverCalled() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // Don't call fetchData() at all

        // Verify getData() was NEVER called
        verify(mockApi, never()).getData();
    }

    @Test
    public void testVerifyAtLeastOnce() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("data");
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.fetchData();
        service.fetchData();

        // Verify getData() was called at least 2 times
        verify(mockApi, atLeast(2)).getData();
    }
}
