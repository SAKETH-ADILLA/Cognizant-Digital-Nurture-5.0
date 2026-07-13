import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Argument Matching
 *
 * Argument matchers let you stub/verify methods based on flexible patterns
 * instead of exact values.
 *
 * Common matchers:
 *   any()           : matches any object (including null)
 *   anyString()     : matches any String
 *   anyInt()        : matches any int
 *   eq("value")     : matches the exact value (useful when mixing matchers)
 *   contains("str") : matches String containing the given substring
 *   startsWith("x") : matches String starting with "x"
 *   isNull()        : matches null
 *   isNotNull()     : matches non-null
 *
 * Rule: if you use ANY matcher in a method call, ALL arguments must use matchers
 */
public class MyServiceTest {

    @Test
    public void testAnyStringMatcher() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub getUserData() for ANY string userId (not just a specific one)
        when(mockApi.getUserData(anyString())).thenReturn("Some User");

        MyService service = new MyService(mockApi);

        // All these calls return "Some User" because anyString() matches all
        assertEquals("Some User", service.fetchUserData("user1"));
        assertEquals("Some User", service.fetchUserData("user999"));
        assertEquals("Some User", service.fetchUserData("random-id"));
    }

    @Test
    public void testExactArgumentVerification() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getUserData("user123")).thenReturn("John");

        MyService service = new MyService(mockApi);
        service.fetchUserData("user123");

        // Verify called with exact argument "user123"
        verify(mockApi).getUserData("user123");
    }

    @Test
    public void testAnyStringVerification() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getUserData(anyString())).thenReturn("User");

        MyService service = new MyService(mockApi);
        service.fetchUserData("anyUser");

        // Verify called with any String argument
        verify(mockApi).getUserData(anyString());
    }

    @Test
    public void testContainsStringMatcher() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub search() for any query containing "spring"
        when(mockApi.search(contains("spring"))).thenReturn("Spring Results");
        when(mockApi.search(contains("java"))).thenReturn("Java Results");

        MyService service = new MyService(mockApi);

        assertEquals("Spring Results", service.searchAndFetch("spring boot"));
        assertEquals("Java Results",   service.searchAndFetch("java basics"));
    }

    @Test
    public void testEqMatcher() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getUserData(eq("admin"))).thenReturn("Admin User");

        MyService service = new MyService(mockApi);
        String result = service.fetchUserData("admin");

        assertEquals("Admin User", result);
        // Verify with eq() matcher
        verify(mockApi).getUserData(eq("admin"));
    }
}
