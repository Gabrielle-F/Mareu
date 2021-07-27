package gabrielle.freville.mareu1.api;

import java.util.ArrayList;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.ui.StrainMeetingsDialogFragment;

public interface ApiService {

    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetings();

    StrainMeetingsDialogFragment getStrainMeetings(int requestCode, int resultCode);

    void deleteMeeting(Meeting meeting);

}
