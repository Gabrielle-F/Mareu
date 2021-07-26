package gabrielle.freville.mareu1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import gabrielle.freville.mareu1.model.Room;

public class StrainMeetingsDialogFragment extends DialogFragment {

    private EditText mEditTextDate;
    private Button mCancel;
    private Button mValidate;
    private Spinner mRoom;
    public DatePickerDialog mDatePickerDialog;

    public int selectedYear;
    public int selectedMonth;
    public int selectedDay;

    public ConfirmFilterListener mOnConfirmFilterListener;
    public Bundle mBundle;
    final Calendar calendar = Calendar.getInstance();

    public static StrainMeetingsDialogFragment newInstance(Room pRoom, String pDate) {
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("Room", pRoom);
        mBundle.putString("Date", pDate);

        StrainMeetingsDialogFragment fragment = new StrainMeetingsDialogFragment();
        fragment.setArguments(mBundle);
        return fragment;
    }

    public void readBundle(){
        mBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_filter, container);
        mEditTextDate = view.findViewById(R.id.strain_meetings_select_date);
        mRoom = view.findViewById(R.id.strain_meetings_spinner);
        mValidate = view.findViewById(R.id.strain_meetings_confirm_button);
        mCancel = view.findViewById(R.id.strain_meetings_cancel_button);
        initDatePicker();
        initListeners();
        initSpinner();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initListeners(){
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilter();
                dismiss();
                StrainMeetingsDialog(mOnConfirmFilterListener);
            }
        });

        mEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    private void initSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<Room>(getContext(), R.layout.support_simple_spinner_dropdown_item, Room.values());
        mRoom.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 930;
        params.height = 1150;
        window.setAttributes(params);
    }

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
        mDatePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Material_Dialog,
                dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void setStringDate(){
        calendar.set(selectedYear, selectedMonth, selectedDay);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String date = formatter.format(calendar.getTime());
        mEditTextDate.setText(date);
    }

    public Room getRoom(){
        return (Room) mRoom.getSelectedItem();

    }

    public void StrainMeetingsDialog(ConfirmFilterListener listener){
        this.mOnConfirmFilterListener = listener;
    }

    public interface ConfirmFilterListener{
        void confirmFilter(Room pRoom, String pDate);
    }

    public void getFilter(){
        final ConfirmFilterListener vOnConfirmFilterListener = mOnConfirmFilterListener;
        final Room vRoomFilter = getRoom();
        final String vDateFilter = mEditTextDate.getEditableText().toString();
        vOnConfirmFilterListener.confirmFilter(vRoomFilter, vDateFilter);
    }

    @Override
    public void showNow(@NonNull FragmentManager manager, @Nullable String tag) {
        readBundle();
        super.showNow(manager, tag);
    }
}
