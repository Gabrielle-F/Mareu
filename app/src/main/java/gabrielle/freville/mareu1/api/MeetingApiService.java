package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;

public class MeetingApiService implements ApiService {

    private List<Meeting> meetings;

    public MeetingApiService(){
        meetings = new ArrayList<>(MeetingGenerator.getMeetings());
    }

    @Override
    public void createMeeting(Meeting meeting) { meetings.add(meeting); }

    @Override
    public ArrayList<Meeting> getMeetings() { return new ArrayList<>(meetings); }

    @Override
    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }

}
