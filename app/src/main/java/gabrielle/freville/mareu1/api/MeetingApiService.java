package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;
import gabrielle.freville.mareu1.ui.FilterMeetingsDialogFragment;

public class MeetingApiService implements ApiService {

    private List<Meeting> meetings;
    private FilterMeetingsDialogFragment dialogFragment;

    public MeetingApiService() {
        meetings = new ArrayList<>(MeetingGenerator.getMeetings());
        dialogFragment = new FilterMeetingsDialogFragment();
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public ArrayList<Meeting> getMeetings() {
        return new ArrayList<>(meetings);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    //** Add after viva **/
    @Override
    public ArrayList<Meeting> filteringMeetings(List<Meeting> listToFilter, Room selectedRoom, String selectedDate) {
        ArrayList<Meeting> meetingArrayList = new ArrayList<>();
        for (Meeting meeting : listToFilter) {
            if (selectedRoom != null && selectedDate != null) {
                if (meeting.getRoom().toString().equals(selectedRoom.toString()) && meeting.getDate().equals(selectedDate)) {
                    meetingArrayList.add(meeting);
                }
            }
            if (selectedRoom != null && selectedDate == null && meeting.getRoom() == selectedRoom) {
                meetingArrayList.add(meeting);
            }
            if (selectedRoom == null && selectedDate != null) {
                if (meeting.getDate().equals(selectedDate)) {
                    meetingArrayList.add(meeting);
                }
            }
        }
        return meetingArrayList;
    }

    //** Add after viva **/
    @Override
    public void confirmFilter(Room selectedRoom, String selectedDate) {
        selectedRoom = dialogFragment.getRoomSpinner();
        if (selectedRoom.toString().isEmpty()) {
            selectedRoom = null;
        }
        selectedDate = dialogFragment.getSelectedDate();
        if (selectedDate.isEmpty()) {
            selectedDate = null;
        }
    }

    //** Add after viva **/
    @Override
    public void clearFilter(Room room, String date) {
        room = dialogFragment.getRoomSpinner();
        date = dialogFragment.getSelectedDate();
        room = null;
        date = null;
    }

}
