package gabrielle.freville.mareu1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingsGenerator {

    public static List<Meetings> DUMMY_MEETINGS = Arrays.asList(
            new Meetings("Réunion A", "14h00", "Peach", "maxime@lamzone.com, alex@lamzone.com"),
            new Meetings("Réunion B", "16h00", "Mario", "paul@lamzone.com, viviane@lamzone.com"),
            new Meetings("Réunion C", "19h00", "Luigi", "amandine@lamzone.com, luc@lamzone.com")
    );

    static List<Meetings> getMeetings() { return new ArrayList<>(DUMMY_MEETINGS); }
}
