package controller;

import java.util.ArrayList;

import gabrielle.freville.mareu1.Meeting;
import gabrielle.freville.mareu1.MeetingsGenerator;
import gabrielle.freville.mareu1.StrainMeetingsDialogFragment;

public class MeetingsApiService implements ApiService {

    private ArrayList<Meeting> mMeetings = new ArrayList<>(MeetingsGenerator.DUMMY_MEETINGS);
    private StrainMeetingsDialogFragment mStrainMeetings = new StrainMeetingsDialogFragment();

    @Override
    public void createMeeting(Meeting meeting) {
            mMeetings.add(meeting);
    }

    @Override
    public ArrayList<Meeting> getMeetings() {
        return mMeetings;
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
