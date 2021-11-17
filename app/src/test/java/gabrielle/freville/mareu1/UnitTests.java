package gabrielle.freville.mareu1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.api.MeetingGenerator;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

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
        assertTrue(service.getCurrentMeetingsList().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getCurrentMeetingsList().get(1);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getCurrentMeetingsList().contains(meetingToDelete));
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getCurrentMeetingsList();
        List<Meeting> expectedMeetings = MeetingGenerator.DUMMY_MEETINGS;
        assertEquals(meetings, expectedMeetings);
    }

    @Test
    public void filterMeetingsWithSuccess() {
        Meeting meetingA = new Meeting("Name", "25/06/1980", "11h00",
                Room.YOSHI, "viviane@lamzone.com, maxime@lamzone.com");
        service.createMeeting(meetingA);
        service.filterMeetings(meetingA.getRoom(), meetingA.getDate());
        List<Meeting> result = service.getCurrentMeetingsList();
        assertTrue(result.contains(meetingA));
    }
}