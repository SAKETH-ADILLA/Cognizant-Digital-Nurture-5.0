import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * Exercise 6: Verifying Interaction Order
 *
 * InOrder: verifies that methods were called in a specific sequence
 *
 * InOrder inOrder = inOrder(mock1, mock2)
 *   : create InOrder verifier for given mocks
 *
 * inOrder.verify(mock).method()
 *   : verify methods in the EXACT order they appear in the test
 *
 * Use case:
 *   - Ensure authentication happens before data access
 *   - Ensure data is validated before it is saved
 *   - Ensure cleanup happens after processing
 */
public class MyServiceTest {

    @Test
    public void testVerifyInteractionOrder() {
        // Arrange
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("data");

        MyService service = new MyService(mockApi);

        // Act: call methods in a specific order
        service.fetchData();
        service.submitData("processed data");
        service.notifyUser("Done!");

        // Create InOrder verifier
        InOrder inOrder = inOrder(mockApi);

        // Verify methods were called in THIS exact order
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).postData("processed data");
        inOrder.verify(mockApi).sendNotification("Done!");
    }

    @Test
    public void testVerifyOrderWithTwoMocks() {
        // Two separate mock objects
        ExternalApi mockApi1 = Mockito.mock(ExternalApi.class);
        ExternalApi mockApi2 = Mockito.mock(ExternalApi.class);

        when(mockApi1.getData()).thenReturn("data1");
        when(mockApi2.getData()).thenReturn("data2");

        // Call methods in order across both mocks
        mockApi1.getData();
        mockApi2.getData();
        mockApi1.postData("result");

        // InOrder can span multiple mocks
        InOrder inOrder = inOrder(mockApi1, mockApi2);
        inOrder.verify(mockApi1).getData();
        inOrder.verify(mockApi2).getData();
        inOrder.verify(mockApi1).postData("result");
    }

    @Test
    public void testSearchBeforePost() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.search(anyString())).thenReturn("results");

        // Simulate: search first, then post the result
        mockApi.search("query");
        mockApi.postData("results");

        InOrder inOrder = inOrder(mockApi);
        // Verify search() was called BEFORE postData()
        inOrder.verify(mockApi).search("query");
        inOrder.verify(mockApi).postData("results");
    }
}
