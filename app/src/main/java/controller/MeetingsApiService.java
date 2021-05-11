package controller;

import java.util.List;

import gabrielle.freville.mareu1.Meetings;
import gabrielle.freville.mareu1.MeetingsGenerator;

public class MeetingsApiService implements ApiService {

    private List<Meetings> mMeetings = MeetingsGenerator.DUMMY_MEETINGS;

    @Override
    public void createMeeting(Meetings meetings) {
            mMeetings.add(meetings);
    }
}
