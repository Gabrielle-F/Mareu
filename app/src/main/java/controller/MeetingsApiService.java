package controller;

import java.util.List;

import gabrielle.freville.mareu1.Meetings;
import gabrielle.freville.mareu1.MeetingsGenerator;
import gabrielle.freville.mareu1.StrainMeetingsDialogFragment;

public class MeetingsApiService implements ApiService {

    private List<Meetings> mMeetings = MeetingsGenerator.DUMMY_MEETINGS;
    private StrainMeetingsDialogFragment mStrainMeetings = new StrainMeetingsDialogFragment();

    @Override
    public void createMeeting(Meetings meetings) {
            mMeetings.add(meetings);
    }

    @Override
    public List<Meetings> getMeetings() {
        return mMeetings;
    }

    @Override
    public StrainMeetingsDialogFragment getStrainMeetings() {
        return mStrainMeetings;
    }

    @Override
    public void deleteMeeting(Meetings meetings) {
        mMeetings.remove(meetings);
    }
}
