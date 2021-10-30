package gabrielle.freville.mareu1.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.model.Meeting;
import gabrielle.freville.mareu1.model.Room;

public class AddMeetingActivity extends AppCompatActivity {

    public EditText editTextDate;
    public EditText editTextTime;
    public Spinner roomSpinner;
    public EditText participants;
    public Button cancel;
    public Button create;

    public int selectedYear;
    public int selectedMonth;
    public int selectedDay;
    public int selectedHour;
    public int selectedMinute;

    private MeetingApiService apiService;
    public DatePickerDialog datePickerDialog;
    public TimePickerDialog timePickerDialog;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        apiService = DependencyInjection.getMeetingsApiService();

        roomSpinner = findViewById(R.id.spinner_room_add_meeting);
        editTextDate = findViewById(R.id.select_date);
        editTextTime = findViewById(R.id.select_hour);
        participants = findViewById(R.id.select_participants);
        cancel = findViewById(R.id.button_cancel);
        create = findViewById(R.id.button_validate);

        initDatePicker();
        initTimePicker();
        initSpinner();
        initListeners();
    }

    private void initListeners(){
        create.setOnClickListener(v -> {
            confirmCreationOfMeeting();
            finish();
        });

        cancel.setOnClickListener(v -> AddMeetingActivity.this.finish());

        editTextDate.setOnClickListener(v -> datePickerDialog.show());

        editTextTime.setOnClickListener(v -> timePickerDialog.show());
    }

    //** Initialisation du Spinner pour le choix de la salle */
    public void initSpinner(){
        ArrayAdapter <Room> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                Room.values());
        roomSpinner.setAdapter(adapter);
    }

    public Room getRoom(){
        return (Room) roomSpinner.getSelectedItem();
    }

    //** Initialisation du TimePicker et affichage de celui-ci puis récupération de la date */
    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
            selectedHour = hourOfDay;
            selectedMinute = minute;
            setStringTime();
        };
        timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Material_Dialog,
                timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    //** Initialisation du DatePicker et affichage de celui-ci puis récupération de la date */
    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            selectedYear = year;
            selectedMonth = month;
            selectedDay = dayOfMonth;
            setStringDate();
        };
        datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Material_Dialog,
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
                participants.getEditableText().toString()
        );
        apiService.createMeeting(meeting);
    }

}
