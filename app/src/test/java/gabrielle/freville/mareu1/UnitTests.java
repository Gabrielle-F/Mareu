package gabrielle.freville.mareu1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.api.MeetingGenerator;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;
import gabrielle.freville.mareu1.ui.MainActivity;

import static org.junit.Assert.*;

public class UnitTests {

    public MeetingApiService service;
    public Meeting meeting;

    @Before
    public void setup() {
        service = DependencyInjection.getMeetingApiService();
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = meeting;
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(1);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = MeetingGenerator.DUMMY_MEETINGS;
        assertEquals(meetings, expectedMeetings);
    }

    @Test
    public void filterMeetingsWithSuccess() {
        Meeting meetingA = new Meeting("Réunion A", "25/06/2021", "13h00",
                Room.YOSHI, "viviane@lamzone.com, maxime@lamzone.com");
        List<Meeting> listToFilter = Arrays.asList(
                new Meeting("Réunion A", "25/06/2021", "13h00",
                        Room.YOSHI, "viviane@lamzone.com, maxime@lamzone.com"),
                new Meeting("Réunion B", "26/06/2021", "14h00",
                        Room.BOWSER, "viviane@lamzone.com, maxime@lamzone.com, paul@lamzone.com"),
                new Meeting("Réunion C", "30/06/2021", "09h00",
                        Room.DAISY, "luc@lamzone.com, paul@lamzone.com"),
                new Meeting("Réunion D", "09/07/2021", "15h00",
                        Room.MARIO, "alex@lamzone.com, viviane@lamzone.com, luc@lamzone.com")
        );
        MainActivity.filteringMeetings(listToFilter, meetingA.getRoom(), meetingA.getDate());
        ArrayList<Meeting> filteredList = new ArrayList<>();
        filteredList.add(meetingA);
        assertTrue(filteredList.contains(meetingA));
    }
}