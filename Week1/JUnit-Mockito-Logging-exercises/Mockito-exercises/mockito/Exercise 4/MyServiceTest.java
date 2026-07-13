import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

/**
 * Exercise 4: Handling Void Methods
 *
 * void methods cannot use when().thenReturn() because they return nothing.
 * Instead use:
 *
 * doNothing().when(mock).voidMethod()
 *   : stub void method to do nothing (default for mocks, but useful when explicit)
 *
 * doAnswer(invocation -> { ... }).when(mock).voidMethod()
 *   : stub void method with custom behavior
 *
 * verify(mock).voidMethod()
 *   : verify the void method was called
 */
public class MyServiceTest {

    @Test
    public void testVoidMethodDoNothing() {
        // Arrange
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub void method - postData() does nothing when called
        doNothing().when(mockApi).postData(anyString());

        MyService service = new MyService(mockApi);

        // Act
        service.submitData("test data");

        // Assert: verify postData was called with the correct argument
        verify(mockApi).postData("test data");
    }

    @Test
    public void testVoidMethodVerifyWithExactArg() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doNothing().when(mockApi).sendNotification(anyString());

        MyService service = new MyService(mockApi);
        service.notifyUser("Hello User!");

        // Verify sendNotification was called with "Hello User!"
        verify(mockApi).sendNotification("Hello User!");
    }

    @Test
    public void testVoidMethodWithDoAnswer() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // doAnswer: execute custom code when void method is called
        doAnswer(invocation -> {
            String data = invocation.getArgument(0);
            System.out.println("Mock intercepted postData call with: " + data);
            return null; // void methods must return null in doAnswer
        }).when(mockApi).postData(anyString());

        MyService service = new MyService(mockApi);
        service.submitData("important data");

        verify(mockApi).postData("important data");
    }

    @Test
    public void testVoidMethodNeverCalled() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        // Don't call notifyUser()

        // Verify sendNotification was NEVER called
        verify(mockApi, never()).sendNotification(anyString());
    }
}
