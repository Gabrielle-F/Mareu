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
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import controller.ApiService;
import controller.DependencyInjection;

public class MainActivity extends AppCompatActivity implements StrainMeetingsDialogFragment.ConfirmFilterListener {

    private FloatingActionButton mFabAddMeeting;
    private ApiService mApiService;
    private String mDate;
    private Room mRoom;

    public RecyclerView mRecyclerView;
    public MeetingRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DependencyInjection.getNewInstanceApiService();
        mRecyclerView = findViewById(R.id.activity_main_recyclerview);
        mFabAddMeeting = findViewById(R.id.button_add_meeting);
        initList();

        mFabAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeetingsActivity.class);
                startActivity(intent);
            }
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initList(){
        mAdapter = new MeetingRecyclerViewAdapter(mApiService.getMeetings());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    //TODO compléter les paramètres du newInstance avec la salle une fois problème de type résolu
    //** Show Pop Up for strain meetings */
    private void showStrainMeetingsDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        StrainMeetingsDialogFragment strainMeetingsDialogFragment = StrainMeetingsDialogFragment.newInstance(mDate);
        strainMeetingsDialogFragment.showNow(fragmentManager, "strain_meeting_dialog");
    }

    //TODO compléter le listener avec la salle
    //** Get Strain Meetings */
    @Override
    public void confirmFilter(String pDate) {
        mDate = pDate;
        initList();
    }

    private void deleteMeeting(Meeting meeting){
        mApiService.deleteMeeting(meeting);
        initList();
    }

    private void addMeeting(Meeting meeting){
        mApiService.createMeeting(meeting);
        initList();
    }
}