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

import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingsApiService;
import gabrielle.freville.mareu1.model.Meeting;

public class AddMeetingActivity extends AppCompatActivity {

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
    public DatePickerDialog mDatePickerDialog;
    public TimePickerDialog mTimePickerDialog;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mApiService = DependencyInjection.getMeetingsApiService();

        mRoomSpinner = findViewById(R.id.spinner_room_add_meeting);
        editTextDate = findViewById(R.id.select_date);
        editTextTime = findViewById(R.id.select_hour);
        mParticipants = findViewById(R.id.select_participants);
        mCancel = findViewById(R.id.button_cancel);
        mCreate = findViewById(R.id.button_validate);

        initDatePicker();
        initTimePicker();
        initSpinner();
    }

    private void initListeners(){
        mCreate.setOnClickListener(v -> {
            confirmCreationOfMeeting();
            finish();
        });

        mCancel.setOnClickListener(v -> AddMeetingActivity.this.finish());

        editTextDate.setOnClickListener(v -> mDatePickerDialog.show());

        editTextTime.setOnClickListener(v -> mTimePickerDialog.show());
    }

    public void initSpinner(){
        ArrayAdapter <Room> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Room.values());
        mRoomSpinner.setAdapter(adapter);
    }

    public Room getRoom(){
        return (Room) mRoomSpinner.getSelectedItem();
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
                timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
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
                dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    //** Attribuer la date au EditText et la convertir en texte */
    public void setStringDate(){
        calendar.set(selectedYear, selectedMonth, selectedDay);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String date = formatter.format(calendar.getTime());
        editTextDate.setText(date);
    }

    //** Attribuer l'heure à l'EditText et la convertir en texte */
    public void setStringTime(){
        calendar.set(selectedHour, selectedMinute);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        String time = formatter.format(calendar.getTime());
        editTextTime.setText(time);
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
