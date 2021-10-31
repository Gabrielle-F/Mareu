package gabrielle.freville.mareu1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.api.MeetingGenerator;
import gabrielle.freville.mareu1.model.Meeting;

import static org.junit.Assert.*;

public class UnitTests {

    public MeetingApiService service;
    public Meeting meeting;

    @Before
    public void setup(){
        service = DependencyInjection.getMeetingsApiService();
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
}