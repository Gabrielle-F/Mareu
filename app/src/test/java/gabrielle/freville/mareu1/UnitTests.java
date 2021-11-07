package gabrielle.freville.mareu1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.api.MeetingGenerator;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;
import gabrielle.freville.mareu1.ui.MainActivity;

import static org.junit.Assert.*;

public class UnitTests {

    public MainActivity mainActivity;
    public Room room = Room.YOSHI ;
    public String date = "10/10/2021";
    public MeetingApiService service;
    public Meeting meeting;

    @Before
    public void setup(){
        service = DependencyInjection.getMeetingApiService();
    }

    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = meeting;
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = service.getMeetings().get(1);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getMeetingsWithSuccess(){
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = MeetingGenerator.DUMMY_MEETINGS;
        assertEquals(meetings, expectedMeetings);
    }

    @Test
    public void filterMeetingsWithSuccess(){
        ArrayList<Meeting> listToFilter = service.getMeetings();
        ArrayList<Meeting> filteredMeetingList = new ArrayList<>();
        mainActivity.filteringMeetings(listToFilter, room, date);
        Meeting meetingToAdd = meeting;
        assertSame(meetingToAdd.getRoom(), room);
        assertEquals(meetingToAdd.getDate(), date);
        assertTrue(filteredMeetingList.contains(meetingToAdd));
    }
}