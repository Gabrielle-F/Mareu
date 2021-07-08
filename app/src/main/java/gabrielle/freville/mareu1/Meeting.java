package gabrielle.freville.mareu1;

import java.io.Serializable;

public class Meeting implements Serializable{

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

    public String getDate() { return mDate; }
    public void setDate(String date) { this.mDate=date; }

    public String getHour() { return mHour; }
    public void setHour(String hour) { this.mHour=hour; }

    public Room getRoom() { return mRoom; }
    public void setRoom(Room room) { this.mRoom=room; }

    public String getParticipants() { return mParticipants; }
    public void setParticipants(String participants) { this.mParticipants=participants; }
}
