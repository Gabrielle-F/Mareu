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
import java.util.List;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public class MainActivity extends AppCompatActivity implements MeetingAdapter.MeetingAdapterInterface {

    private MeetingApiService apiService;
    private String date;
    private Room room;
    private ArrayList<Meeting> meetings;

    public RecyclerView recyclerView;
    public MeetingAdapter adapter;
    public Meeting meetingToDelete;
    public MeetingAdapter.MeetingAdapterInterface meetingAdapterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = DependencyInjection.getMeetingApiService();
        recyclerView = findViewById(R.id.activity_main_recyclerview);
        FloatingActionButton fabAddMeeting = findViewById(R.id.button_add_meeting);
        meetingAdapterInterface = this;
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
        meetings = apiService.getCurrentMeetingsList();
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
        updateList(apiService.getCurrentMeetingsList());
    }

    private void showFilterMeetingsDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FilterMeetingsDialogFragment filterMeetingsDialogFragment = FilterMeetingsDialogFragment.newInstance(room, date);
        filterMeetingsDialogFragment.showNow(fragmentManager, "filter_meeting_dialog");
    }

    //** Add after viva **/
    public void getFilter(Room selectedRoom, String selectedDate) {
        room = selectedRoom;
        date = selectedDate;
        apiService.confirmFilter(room, date);
    }

    //** Add after viva **/
    public void filteringMeetingList(List<Meeting> listToFilter) {
        Room selectedRoom = room;
        String selectedDate = date;
        getFilter(selectedRoom, selectedDate);
        apiService.filteringMeetings(listToFilter, selectedRoom, selectedDate);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingToDelete = meeting;
        apiService.deleteMeeting(meetingToDelete);
        meetings.remove(meeting);
        adapter.notifyDataSetChanged();
    }
}