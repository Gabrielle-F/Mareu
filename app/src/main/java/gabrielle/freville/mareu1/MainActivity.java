package gabrielle.freville.mareu1;

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

import gabrielle.freville.mareu1.api.ApiService;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.model.Meeting;

public class MainActivity extends AppCompatActivity implements StrainMeetingsDialogFragment.ConfirmFilterListener {

    private FloatingActionButton mFabAddMeeting;
    private ApiService mApiService;
    private String mDate;
    private Room mRoom;
    private ArrayList<Meeting> mMeetings;

    public RecyclerView mRecyclerView;
    public MeetingRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DependencyInjection.getMeetingsApiService();
        mRecyclerView = findViewById(R.id.activity_main_recyclerview);
        mFabAddMeeting = findViewById(R.id.button_add_meeting);
        initList();

        mFabAddMeeting.setOnClickListener(v -> {
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
        switch (item.getItemId()) {
            case R.id.strain_action:
                showStrainMeetingsDialog();
                confirmFilter(mRoom, mDate);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //** Initialiser la liste des réunions */
    public void initList(){
        mMeetings = mApiService.getMeetings();
        Collections.sort(mMeetings);
        mAdapter = new MeetingRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(mAdapter);
    }

    //** Mettre la liste à jour */
    private void updateList(){
        mMeetings.clear();
        mMeetings.addAll(mApiService.getMeetings());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList();
    }

    //** Show Pop Up for strain meetings */
    private void showStrainMeetingsDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        StrainMeetingsDialogFragment strainMeetingsDialogFragment = StrainMeetingsDialogFragment.newInstance(mRoom, mDate);
        strainMeetingsDialogFragment.showNow(fragmentManager, "strain_meeting_dialog");
    }

    //** Get Strain Meetings */
    @Override
    public void confirmFilter(Room pRoom, String pDate) {
        mRoom = pRoom;
        mDate = pDate;
        updateList();
    }
}