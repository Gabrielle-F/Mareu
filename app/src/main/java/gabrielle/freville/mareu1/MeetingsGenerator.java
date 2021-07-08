package gabrielle.freville.mareu1;

import android.graphics.HardwareRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingsGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("25/06/21", "14h00", Room.PEACH, "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting("15/06/21", "16h00", Room.MARIO, "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting("10/06/21", "19h00", Room.LUIGI, "amandine@lamzone.com, luc@lamzone.com"),
            new Meeting("03/07/21", "12h00", Room.WARIO, "gfrev5@lamzone.com, yan10@lamzone.com"),
            new Meeting("30/08/21", "16hOO", Room.LUIGI, "mario@lamzone.com, luigi@lamzone.com"),
            new Meeting("25/08/21", "11hOO", Room.MELODY, "una@lamzone.com, vivian@lamzone.com"),
            new Meeting("09/07/21", "09h00", Room.BOWSER, "tom@lamzone.com, chloe@lamzone.com"),
            new Meeting("20/07/21", "14h00", Room.DAISY, "mat@lamzone.com, jena@lamzone.com"),
            new Meeting("15/08/21", "16h30", Room.YOSHI, "luc@lamzone.com, manon@lamzone.com"),
            new Meeting("19/06/21", "14h20", Room.TOAD, "yann@lamzone.com, vale@lamzone.com"),
            new Meeting("08/07/21", "11h40", Room.PEACH, "jay@lamzone.com, nico@lamzone.com")

    );

    static List<Meeting> getMeetings() { return new ArrayList<>(DUMMY_MEETINGS); }
}
