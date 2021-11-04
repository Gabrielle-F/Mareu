package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gabrielle.freville.mareu1.model.Room;
import gabrielle.freville.mareu1.model.Meeting;

public class MeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Réunion A", "25/06/2021", "14h00", Room.PEACH, "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting("Réunion B", "25/06/2021", "16h00", Room.MARIO, "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting("Réunion C", "25/06/2021", "19h00", Room.LUIGI, "viviane@lamzone.com, luc@lamzone.com"),
            new Meeting("Réunion D", "25/07/2021", "12h00", Room.WARIO, "luc@lamzone.com, maxime@lamzone.com"),
            new Meeting("Réunion E", "25/08/2021", "11hOO", Room.MELODY, "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting("Réunion F", "25/07/2021", "09h00", Room.BOWSER, "alex@lamzone.com, maxime@lamzone.com"),
            new Meeting("Réunion G", "25/08/2021", "16h30", Room.YOSHI, "luc@lamzone.com, viviane@lamzone.com"),
            new Meeting("Réunion H", "25/06/2021", "14h20", Room.TOAD, "viviane@lamzone.com, paul@lamzone.com"),
            new Meeting("Réunion I", "25/07/2021", "11h40", Room.PEACH, "maxime@lamzone.com, alex@lamzone.com")

    );

    static List<Meeting> getMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
