package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.StrainMeetingsDialogFragment;

public class MeetingsApiService implements ApiService {

    private List<Meeting> mMeetings;
    private StrainMeetingsDialogFragment mStrainMeetings;

    public MeetingsApiService(){
        mMeetings = new ArrayList<>(MeetingGenerator.DUMMY_MEETINGS);
        mStrainMeetings = new StrainMeetingsDialogFragment();
    }

    @Override
    public void createMeeting(Meeting meeting) {
            mMeetings.add(meeting);
    }

    @Override
    public ArrayList<Meeting> getMeetings() {
        return new ArrayList<>(mMeetings);
    }

    @Override
    public StrainMeetingsDialogFragment getStrainMeetings(int requestCode, int resultCode) {
        return mStrainMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

}
