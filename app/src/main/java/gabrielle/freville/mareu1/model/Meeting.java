package gabrielle.freville.mareu1.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Meeting implements Serializable, Comparable<Meeting> {

    private String hour;
    private String date;
    private Room room;
    private String participants;

    public Meeting(String date, String hour, Room room, String participants) {
        this.date = date;
        this.hour = hour;
        this.room = room;
        this.participants = participants;
    }

    public Date getFormattedDate() {
        String dateString = date + " " + hour;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH'h'mm", Locale.FRANCE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public Room getRoom() {
        return room;
    }

    public String getParticipants() {
        return participants;
    }

    @Override
    public int compareTo(Meeting other) {
        return getFormattedDate().compareTo(other.getFormattedDate());
    }
}
