package gabrielle.freville.mareu1.api;

public class DependencyInjection {

    private static MeetingsApiService mApiService = new MeetingsApiService();

    public static MeetingsApiService getMeetingsApiService() { return mApiService; }
}
