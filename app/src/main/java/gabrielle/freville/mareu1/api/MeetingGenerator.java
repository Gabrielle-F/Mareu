package gabrielle.freville.mareu1.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gabrielle.freville.mareu1.model.Room;
import gabrielle.freville.mareu1.model.Meeting;

public class MeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("25/06/2021", "14h00", Room.PEACH, "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting("25/06/2021", "16h00", Room.MARIO, "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting("25/06/2021", "19h00", Room.LUIGI, "amandine@lamzone.com, luc@lamzone.com"),
            new Meeting("25/07/2021", "12h00", Room.WARIO, "gfrev5@lamzone.com, yan10@lamzone.com"),
            new Meeting("25/08/2021", "11hOO", Room.MELODY, "una@lamzone.com, vivian@lamzone.com"),
            new Meeting("25/07/2021", "09h00", Room.BOWSER, "alex@lamzone.com, chloe@lamzone.com"),
            new Meeting("25/08/2021", "16h30", Room.YOSHI, "jay@lamzone.com, manon@lamzone.com"),
            new Meeting("25/06/2021", "14h20", Room.TOAD, "yann@lamzone.com, vale@lamzone.com"),
            new Meeting("25/07/2021", "11h40", Room.PEACH, "jay@lamzone.com, nico@lamzone.com")

    );

    static List<Meeting> getMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
