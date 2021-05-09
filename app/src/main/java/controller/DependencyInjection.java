package controller;

public class DependencyInjection {

    private static MeetingsApiService mApiService = new MeetingsApiService();

    public static MeetingsApiService getMeetingsApiService() { return mApiService; }

    public static MeetingsApiService getNewInstanceApiService() { return new MeetingsApiService(); }
}
