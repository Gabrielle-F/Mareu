package controller;

import java.util.List;

import gabrielle.freville.mareu1.Meetings;
import gabrielle.freville.mareu1.StrainMeetingsDialogFragment;

public interface ApiService {

    void createMeeting(Meetings meetings);

    List<Meetings> getMeetings();

    StrainMeetingsDialogFragment getStrainMeetings();

    void deleteMeeting(Meetings meetings);
}
