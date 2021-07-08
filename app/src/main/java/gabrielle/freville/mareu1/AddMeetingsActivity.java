package gabrielle.freville.mareu1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import controller.DependencyInjection;
import controller.MeetingsApiService;

public class AddMeetingsActivity extends AppCompatActivity {

    public EditText editTextDate;
    public EditText editTextTime;
    public int selectedYear;
    public int selectedMonth;
    public int selectedDay;
    public int selectedHour;
    public int selectedMinute;
    public Spinner mRoomSpinner;
    public EditText mParticipants;
    public Button mCancel;
    public Button mCreate;
    public Room mRoom;
    private MeetingsApiService mApiService;
    public boolean is24H = true;
    public DatePickerDialog mDatePickerDialog;
    public TimePickerDialog mTimePickerDialog;
    final Calendar calendar = Calendar.getInstance();
    public int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DependencyInjection.getMeetingsApiService();

        mRoomSpinner = findViewById(R.id.spinner_room_add_meeting);
        editTextDate = findViewById(R.id.select_date);
        editTextTime = findViewById(R.id.select_hour);
        mParticipants = findViewById(R.id.select_participants);
        mParticipants.setHint("Entrez le mail des participants");
        mCancel = findViewById(R.id.button_cancel);
        mCreate = findViewById(R.id.button_validate);
        initDatePicker();
        initTimePicker();
        setAdapterToSpinner();

        mCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                confirmCreationOfMeeting();
                finish();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMeetingsActivity.this.finish();
            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show();
            }
        });
    }

    public void setAdapterToSpinner(){
        ArrayAdapter <Room> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Room.values());
        this.mRoomSpinner.setAdapter(adapter);
        this.mRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter1 = parent.getAdapter();
                Room room = (Room) adapter1.getItem(position);
                Toast.makeText(getApplicationContext(), "Sélectionnez une salle:" + room.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public Room getRoom(){
        Room pRoom = (Room) mRoomSpinner.getSelectedItem();
        return pRoom;
    }

    //** Initialisation du TimePicker et affichage de celui-ci puis récupération de la date */
    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay;
                selectedMinute = minute;
                setStringTime();
            }
        };
        mTimePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, selectedHour, selectedMinute, is24H);
    }

    //** Attribuer le temps à l'EditText et la convertir en texte */
    public void setStringTime(){
        calendar.set(selectedHour, selectedMinute);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        String time = formatter.format(calendar.getTime());
        editTextTime.setText(time);
    }

    //** Initialisation du DatePicker et affichage de celui-ci puis récupération de la date */
    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = month;
                selectedDay = dayOfMonth;
                setStringDate();
            }
        };
        mDatePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDay);
    }

    //** Attribuer la date au EditText et la convertir en texte */
    public void setStringDate(){
        calendar.set(selectedYear, selectedMonth, selectedDay);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String date = formatter.format(calendar.getTime());
        editTextDate.setText(date);
    }

    private void confirmCreationOfMeeting(){
        Meeting meeting = new Meeting(
                editTextDate.getEditableText().toString(),
                editTextTime.getEditableText().toString(),
                getRoom(),
                mParticipants.getEditableText().toString()
        );
        mApiService.createMeeting(meeting);
    }

}
