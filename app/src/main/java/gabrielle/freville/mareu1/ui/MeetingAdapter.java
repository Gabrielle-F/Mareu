package gabrielle.freville.mareu1.ui;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.model.Meeting;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private final List<Meeting> meetings;
    public MeetingAdapterInterface adapterInterface;

    public MeetingAdapter(List<Meeting> items, MeetingAdapterInterface meetingAdapterInterface) {
        meetings = items;
        adapterInterface = meetingAdapterInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);

        holder.meetingHourAndRoom.setText(holder.itemView.getContext().getString
                (R.string.meeting_title,
                        meeting.getHour(),
                        meeting.getRoom()));
        holder.meetingParticipants.setText(meeting.getParticipants());
        ImageViewCompat.setImageTintList(
                holder.meetingSticker,
                ColorStateList.valueOf(
                        ContextCompat.getColor(
                                holder.itemView.getContext(),
                                meeting.getRoom().getColor()
                        )
                )
        );
        holder.deleteButton.setOnClickListener(v -> adapterInterface.deleteMeeting(meeting));
    }

    public interface MeetingAdapterInterface {
        void deleteMeeting(Meeting meeting);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView meetingSticker;
        TextView meetingHourAndRoom;
        TextView meetingParticipants;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            meetingSticker = itemView.findViewById(R.id.meeting_sticker);
            meetingHourAndRoom = itemView.findViewById(R.id.meeting_hour_room);
            meetingParticipants = itemView.findViewById(R.id.meeting_participants);
            deleteButton = itemView.findViewById(R.id.meeting_delete_button);
        }
    }
}
