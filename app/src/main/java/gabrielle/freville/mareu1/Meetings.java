package gabrielle.freville.mareu1;

public class Meetings {

    private String mOrder;
    private String mHour;
    private String mRoom;
    private String mParticipants;

    public Meetings(String order, String hour, String room, String participants) {
        this.mOrder=order;
        this.mHour=hour;
        this.mRoom=room;
        this.mParticipants=participants;
    }

    public String getOrder() { return mOrder; }
    public void setOrder(String order) { this.mOrder=order; }

    public String getHour() { return mHour; }
    public void setHour(String hour) { this.mHour=hour; }

    public String getRoom() { return mRoom; }
    public void setRoom(String room) { this.mRoom=room; }

    public String getParticipants() { return mParticipants; }
    public void setParticipants(String participants) { this.mParticipants=participants; }
}
