package gabrielle.freville.mareu1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import controller.ApiService;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeeting;

    public MeetingRecyclerViewAdapter(List<Meeting> items){
        mMeeting = items;
    }
    public ApiService mApiService;
    public Room mRoomSticker;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Meeting meeting = mMeeting.get(position);

        holder.mMeetingHourAndRoom.setText(holder.itemView.getContext().getString(R.string.meeting_title, meeting.getHour(), meeting.getRoom()));
        holder.mMeetingParticipants.setText(meeting.getParticipants());
        holder.mMeetingSticker.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), meeting.getRoom().color), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mApiService.deleteMeeting(meeting);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mMeetingSticker;
        TextView mMeetingHourAndRoom;
        TextView mMeetingParticipants;
        ImageButton mDeleteMeeting;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mMeetingSticker = itemView.findViewById(R.id.meeting_sticker);
            mMeetingHourAndRoom = itemView.findViewById(R.id.meeting_hour_room);
            mMeetingParticipants = itemView.findViewById(R.id.meeting_participants);
            mDeleteMeeting = itemView.findViewById(R.id.meeting_delete_button);
        }
    }
}
