package gabrielle.freville.mareu1.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import gabrielle.freville.mareu1.Room;

public class Meeting implements Serializable, Comparable<Meeting> {

    private String mHour;
    private String mDate;
    private Room mRoom;
    private String mParticipants;

    public Meeting(String date, String hour, Room room, String participants) {
        this.mDate=date;
        this.mHour=hour;
        this.mRoom=room;
        this.mParticipants=participants;
    }

    public Date getFormattedDate(){
        String dateString = mDate + " " + mHour;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH'h'mm", Locale.FRANCE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String getDate() { return mDate; }
    public void setDate(String date) { this.mDate=date; }

    public String getHour() { return mHour; }
    public void setHour(String hour) { this.mHour=hour; }

    public Room getRoom() { return mRoom; }
    public void setRoom(Room room) { this.mRoom=room; }

    public String getParticipants() { return mParticipants; }
    public void setParticipants(String participants) { this.mParticipants=participants; }

    @Override
    public int compareTo(Meeting other) {
        return getFormattedDate().compareTo(other.getFormattedDate());
    }
}
