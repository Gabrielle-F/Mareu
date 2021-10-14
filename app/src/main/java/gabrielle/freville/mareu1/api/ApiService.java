package gabrielle.freville.mareu1.api;

import java.util.ArrayList;

import gabrielle.freville.mareu1.model.Meeting;

public interface ApiService {

    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

}
