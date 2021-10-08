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
        this.date=date;
        this.hour=hour;
        this.room=room;
        this.participants=participants;
    }

    public Date getFormattedDate(){
        String dateString = date + " " + hour;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH'h'mm", Locale.FRANCE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date=date; }

    public String getHour() { return hour; }
    public void setHour(String hour) { this.hour=hour; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room=room; }

    public String getParticipants() { return participants; }
    public void setParticipants(String participants) { this.participants=participants; }

    @Override
    public int compareTo(Meeting other) {
        return getFormattedDate().compareTo(other.getFormattedDate());
    }
}
