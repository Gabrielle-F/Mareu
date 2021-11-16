package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public interface ApiService {

    void createMeeting(Meeting meeting);

    ArrayList<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    //** Add after viva **/
    ArrayList<Meeting> filteringMeetings(List<Meeting> listToFilter, Room selectedRoom, String selectedDate);

    //** Add after viva **/
    void confirmFilter(Room selectedRoom, String selectedDate);

    //** Add after viva */
    void clearFilter(Room room, String date);

}
