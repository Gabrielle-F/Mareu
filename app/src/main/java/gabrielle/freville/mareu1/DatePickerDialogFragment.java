package gabrielle.freville.mareu1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private int mDay;
    private int mMonth;
    private int mYear;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.mDay=dayOfMonth;
        this.mMonth=month;
        this.mYear=year;
    }

    public int getDay() { return mDay; }
    public int getMonth() { return mMonth; }
    public int getYear() { return mYear; }
}
