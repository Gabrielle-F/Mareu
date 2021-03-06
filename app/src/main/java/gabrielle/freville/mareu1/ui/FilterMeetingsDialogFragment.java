package gabrielle.freville.mareu1.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.model.Room;

public class FilterMeetingsDialogFragment extends DialogFragment {

    private EditText editTextDate;
    private Button clearFilterButton;
    private Button validateButton;
    private Spinner roomSpinner;
    public DatePickerDialog datePickerDialog;
    public MeetingApiService apiService;

    public int selectedYear;
    public int selectedMonth;
    public int selectedDay;

    public Bundle bundle;
    final Calendar calendar = Calendar.getInstance();
    private ConfirmFilterListener onConfirmFilterListener;

    public void readBundle() {
        bundle = getArguments();
    }

    public interface ConfirmFilterListener {
        void confirmFilter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_filter, container);
        apiService = DependencyInjection.getMeetingApiService();
        editTextDate = view.findViewById(R.id.filter_meetings_select_date);
        roomSpinner = view.findViewById(R.id.filter_meetings_spinner);
        validateButton = view.findViewById(R.id.filter_meetings_confirm_button);
        clearFilterButton = view.findViewById(R.id.filter_meetings_cancel_button);

        initDatePicker();
        initListeners();
        initSpinner();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onConfirmFilterListener = (ConfirmFilterListener) context;
    }

    private void initListeners() {
        clearFilterButton.setOnClickListener(v -> {
            apiService.clearFilter();
            onConfirmFilterListener.confirmFilter();
            dismiss();
        });

        validateButton.setOnClickListener(v -> {
            apiService.confirmFilter(getRoomSpinner(), getSelectedDate());
            onConfirmFilterListener.confirmFilter();
            dismiss();
        });

        editTextDate.setOnClickListener(v -> datePickerDialog.show());
    }

    private void initSpinner() {
        ArrayAdapter<Room> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                Room.values());
        roomSpinner.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        WindowManager.LayoutParams params = Objects.requireNonNull(window).getAttributes();
        params.width = 930;
        params.height = 1150;
        window.setAttributes(params);
    }

    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            selectedYear = year;
            selectedMonth = month;
            selectedDay = dayOfMonth;
            setStringDate();
        };
        datePickerDialog = new DatePickerDialog(requireContext(), android.R.style.Theme_Material_Dialog,
                dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void setStringDate() {
        calendar.set(selectedYear, selectedMonth, selectedDay);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String date = formatter.format(calendar.getTime());
        editTextDate.setText(date);
    }

    public String getSelectedDate() {
        return editTextDate.getEditableText().toString();
    }

    public Room getRoomSpinner() {
        return (Room) roomSpinner.getSelectedItem();
    }

    @Override
    public void showNow(@NonNull FragmentManager manager, @Nullable String tag) {
        readBundle();
        super.showNow(manager, tag);
    }


}
