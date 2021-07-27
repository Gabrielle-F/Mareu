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
import gabrielle.freville.mareu1.api.ApiService;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.model.Meeting;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeeting;

    public MeetingRecyclerViewAdapter(List<Meeting> items){
        mMeeting = items;
    }
    public ApiService mApiService = DependencyInjection.getMeetingsApiService();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeeting.get(position);

        holder.mMeetingHourAndRoom.setText(holder.itemView.getContext().getString(R.string.meeting_title, meeting.getHour(), meeting.getRoom()));
        holder.mMeetingParticipants.setText(meeting.getParticipants());
        ImageViewCompat.setImageTintList(
                holder.mMeetingSticker,
                ColorStateList.valueOf(
                        ContextCompat.getColor(
                                holder.itemView.getContext(),
                                meeting.getRoom().getColor()
                        )
                )
        );

     /* holder.mDeleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mApiService.deleteMeeting(meeting);
            }
        });*/
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
