import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 5: Mocking and Stubbing with Multiple Returns
 *
 * when(mock.method()).thenReturn(val1, val2, val3)
 *   : returns val1 on 1st call, val2 on 2nd call, val3 on 3rd+ calls
 *     (last value is repeated for any subsequent calls)
 *
 * when(mock.method())
 *   .thenReturn(val1)
 *   .thenReturn(val2)
 *   .thenThrow(new RuntimeException())
 *   : chain multiple behaviors
 *
 * Use case: testing retry logic, pagination, or stateful API responses
 */
public class MyServiceTest {

    @Test
    public void testMultipleReturnValues() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub getData() to return different values on consecutive calls
        when(mockApi.getData())
                .thenReturn("First Call")
                .thenReturn("Second Call")
                .thenReturn("Third Call");

        MyService service = new MyService(mockApi);

        // Each call returns the next stubbed value
        assertEquals("First Call",  service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call",  service.fetchData());
        // 4th call still returns last value ("Third Call")
        assertEquals("Third Call",  service.fetchData());
    }

    @Test
    public void testMultipleReturnsShorthand() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Shorthand: pass all values in one thenReturn()
        when(mockApi.getData()).thenReturn("A", "B", "C");

        MyService service = new MyService(mockApi);

        assertEquals("A", service.fetchData());
        assertEquals("B", service.fetchData());
        assertEquals("C", service.fetchData());
    }

    @Test
    public void testReturnThenThrow() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Return value on first call, throw exception on second call
        when(mockApi.getData())
                .thenReturn("Success")
                .thenThrow(new RuntimeException("API failed"));

        MyService service = new MyService(mockApi);

        // First call succeeds
        assertEquals("Success", service.fetchData());

        // Second call throws exception
        assertThrows(RuntimeException.class, () -> service.fetchData());
    }

    @Test
    public void testPaginatedResults() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Simulate paginated API: different data on each page request
        when(mockApi.search("page"))
                .thenReturn("Page 1 Results")
                .thenReturn("Page 2 Results")
                .thenReturn("No more results");

        MyService service = new MyService(mockApi);

        assertEquals("Page 1 Results",  service.searchAndFetch("page"));
        assertEquals("Page 2 Results",  service.searchAndFetch("page"));
        assertEquals("No more results", service.searchAndFetch("page"));
    }
}
