package gabrielle.freville.mareu1.api;

import java.util.List;

import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public interface ApiService {

    void createMeeting(Meeting meeting);

    List<Meeting> getCurrentMeetingsList();

    void deleteMeeting(Meeting meeting);

    void confirmFilter(Room selectedRoom, String selectedDate);

    Room getCurrentRoomFilter();

    String getCurrentDateFilter();

    void clearFilter();

}
