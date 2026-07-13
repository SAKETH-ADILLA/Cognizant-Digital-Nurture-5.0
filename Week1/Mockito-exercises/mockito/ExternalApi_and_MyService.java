// ===== ExternalApi.java =====
// Place in src/main/java/
// Interface representing an external API dependency
public interface ExternalApi {

    // Returns data from external source
    String getData();

    // Returns data for a specific user
    String getUserData(String userId);

    // Posts data to external source (void)
    void postData(String data);

    // Sends notification (void)
    void sendNotification(String message);

    // Returns different data based on query
    String search(String query);
}


// ===== MyService.java =====
// Place in src/main/java/
// Service class that depends on ExternalApi
public class MyService {

    private final ExternalApi externalApi;

    // Constructor injection - makes it easy to inject mock in tests
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchUserData(String userId) {
        return externalApi.getUserData(userId);
    }

    public void submitData(String data) {
        externalApi.postData(data);
    }

    public void notifyUser(String message) {
        externalApi.sendNotification(message);
    }

    public String searchAndFetch(String query) {
        return externalApi.search(query);
    }
}
