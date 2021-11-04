package gabrielle.freville.mareu1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public class MainActivity extends AppCompatActivity implements FilterMeetingsDialogFragment.ConfirmFilterListener, MeetingAdapter.MeetingAdapterInterface {

    private MeetingApiService apiService;
    private String date;
    private Room room;
    private ArrayList<Meeting> meetings;

    public RecyclerView recyclerView;
    public MeetingAdapter adapter;
    public Meeting meetingToDelete;
    public MeetingAdapter.MeetingAdapterInterface meetingAdapterInterface;
    public FilterMeetingsDialogFragment.ConfirmFilterListener confirmFilterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = DependencyInjection.getMeetingsApiService();
        recyclerView = findViewById(R.id.activity_main_recyclerview);
        FloatingActionButton fabAddMeeting = findViewById(R.id.button_add_meeting);
        meetingAdapterInterface = this;
        confirmFilterListener = this;
        initList();

        fabAddMeeting.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filter_action) {
            showFilterMeetingsDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initList() {
        meetings = apiService.getMeetings();
        Collections.sort(meetings);
        adapter = new MeetingAdapter(meetings, this);
        recyclerView.setAdapter(adapter);
    }

    private void updateList(ArrayList<Meeting> newList) {
        meetings.clear();
        meetings.addAll(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList(apiService.getMeetings());
    }

    private void showFilterMeetingsDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FilterMeetingsDialogFragment filterMeetingsDialogFragment = FilterMeetingsDialogFragment.newInstance(room, date);
        filterMeetingsDialogFragment.showNow(fragmentManager, "strain_meeting_dialog");
    }

    public ArrayList<Meeting> filteringMeetings(ArrayList<Meeting> listToFilter, Room selectedRoom, String selectedDate) {
        ArrayList<Meeting> meetingArrayList = new ArrayList<>();
        for (Meeting meeting : listToFilter) {
            if (selectedRoom != null && selectedDate != null) {
                if (meeting.getRoom().toString().equals(selectedRoom.toString()) && meeting.getDate().equals(selectedDate)) {
                    meetingArrayList.add(meeting);
                }
            }
            if (selectedRoom != null && selectedDate == null && meeting.getRoom() == selectedRoom) {
                meetingArrayList.add(meeting);
            }
            if (selectedRoom == null && selectedDate != null) {
                if (meeting.getDate().equals(selectedDate)) {
                    meetingArrayList.add(meeting);
                }
            }
        }
        return meetingArrayList;
    }

    @Override
    public void confirmFilter(Room pRoom, String pDate) {
        room = pRoom;
        date = pDate;
        ArrayList<Meeting> listToFilter = apiService.getMeetings();
        updateList(filteringMeetings(listToFilter, pRoom, pDate));
    }

    @Override
    public void clearFilter() {
        room = null;
        date = null;
        updateList(apiService.getMeetings());
    }
    
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingToDelete = meeting;
        apiService.deleteMeeting(meetingToDelete);
        meetings.remove(meeting);
        adapter.notifyDataSetChanged();
    }
}