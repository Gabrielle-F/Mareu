package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public class MeetingApiService implements ApiService {

    private List<Meeting> allMeetings;
    private List<Meeting> filteredMeetings;

    public MeetingApiService() {
        allMeetings = MeetingGenerator.getMeetings();
    }

    @Override
    public void createMeeting(Meeting meeting) {
        allMeetings.add(meeting);
    }

    @Override
    public List<Meeting> getCurrentMeetingsList() {
        if (filteredMeetings != null) {
            return filteredMeetings;
        } else return allMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        allMeetings.remove(meeting);
    }

    //** Add after viva **/
    public void filterMeetings(Room selectedRoom, String selectedDate) {
        ArrayList<Meeting> meetingArrayList = new ArrayList<>();
        for (Meeting meeting : allMeetings) {
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
        filteredMeetings = meetingArrayList;
    }

    //** Add after viva **/
    @Override
    public void confirmFilter(Room selectedRoom, String selectedDate) {
        if (selectedRoom.toString().isEmpty()) {
            selectedRoom = null;
        }
        if (selectedDate.isEmpty()) {
            selectedDate = null;
        }
        filterMeetings(selectedRoom, selectedDate);
    }

    //** Add after viva **/
    @Override
    public void clearFilter(Room room, String date) {
        room = null;
        date = null;
    }

}
