package controller;

import java.util.ArrayList;

import gabrielle.freville.mareu1.Meeting;
import gabrielle.freville.mareu1.StrainMeetingsDialogFragment;

public interface ApiService {

    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetings();

    StrainMeetingsDialogFragment getStrainMeetings(int requestCode, int resultCode);

    void deleteMeeting(Meeting meeting);

}
