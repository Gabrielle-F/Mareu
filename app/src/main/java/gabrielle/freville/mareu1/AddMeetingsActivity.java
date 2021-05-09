package gabrielle.freville.mareu1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import controller.DependencyInjection;
import controller.MeetingsApiService;

public class AddMeetingsActivity extends AppCompatActivity {

    private EditText mOrder;
    private EditText mHour;
    private EditText mRoom;
    private EditText mParticipants;
    private Button mCreate;
    private MeetingsApiService mApiService;
    private View.OnClickListener mOnClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DependencyInjection.getMeetingsApiService();
        init();
    }

    private void init() {
        mHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { mCreate.setEnabled(s.length() > 0);

            }
        });

        mRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { mCreate.setEnabled(s.length() > 0);

            }
        });
        mOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meetings meetings = new Meetings(
                        mOrder.getText().toString(),
                        mHour.getText().toString(),
                        mRoom.getText().toString(),
                        mParticipants.getText().toString()
                );
                mApiService.createMeeting(meetings);
                finish();
            }
        };
        mCreate.setOnClickListener(mOnClick);
    }

}
