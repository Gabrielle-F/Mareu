package gabrielle.freville.mareu1.api;

public class DependencyInjection {

    private static MeetingApiService apiService = new MeetingApiService();

    public static MeetingApiService getMeetingsApiService() {
        return apiService;
    }
}
