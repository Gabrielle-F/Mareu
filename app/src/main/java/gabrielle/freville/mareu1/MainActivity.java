package gabrielle.freville.mareu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import controller.ApiService;
import controller.DependencyInjection;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mFabAddMeeting;
    private ApiService mApiService;
    private View.OnClickListener mOnClick;
    private Meetings mMeetings;

    enum mRooms{
        Mario, Peach, Luigi, Wario, Toad, Yoshi, Luma, Melody, Bowser, Daisy
    }

    mRooms room1 = mRooms.Mario;
    mRooms room2 = mRooms.Toad;
    mRooms room3 = mRooms.Bowser;
    mRooms room4 = mRooms.Daisy;
    mRooms room5 = mRooms.Luigi;
    mRooms room6 = mRooms.Yoshi;
    mRooms room7 = mRooms.Melody;
    mRooms room8 = mRooms.Wario;
    mRooms room9 = mRooms.Peach;
    mRooms room10 = mRooms.Luma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = DependencyInjection.getMeetingsApiService();
        mOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  addMeetings(mMeetings);
            }
        };
        mFabAddMeeting.setOnClickListener(mOnClick);

        if(room1 == mRooms.Mario){
            getDrawable(R.drawable.item_brightred_sticker);
            System.out.println("Mario");
        }
        if(room2 == mRooms.Toad){
            getDrawable(R.drawable.item_lightred_sticker);
            System.out.println("Toad");
        }
        if(room3 == mRooms.Bowser){
            getDrawable(R.drawable.item_lightorange_sticker);
            System.out.println("Bowser");
        }
        if(room4 == mRooms.Daisy){
            getDrawable(R.drawable.item_lightyellow_sticker);
            System.out.println("Daisy");
        }
        if(room5 == mRooms.Luigi){
            getDrawable(R.drawable.item_brightgreen_sticker);
            System.out.println("Luigi");
        }
        if(room6 == mRooms.Yoshi){
            getDrawable(R.drawable.item_lightgreen_sticker);
            System.out.println("Yoshi");
        }
        if(room7 == mRooms.Melody){
            getDrawable(R.drawable.item_lightblue_sticker);
            System.out.println("Melody");
        }
        if(room8 == mRooms.Wario){
            getDrawable(R.drawable.item_lightpurple_sticker);
            System.out.println("Wario");
        }
        if(room9 == mRooms.Peach){
            getDrawable(R.drawable.item_lightpink_sticker);
            System.out.println("Peach");
        }
        if(room10 == mRooms.Luma){
            getDrawable(R.drawable.item_luma_sticker);
            System.out.println("Luma");
        }
    }

    private void addMeetings(Meetings meetings){
        Intent intent = new Intent(this, AddMeetingsActivity.class);
        intent.putExtra("Meeting", meetings);
        startActivity(intent);
    }

}