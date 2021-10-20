package gabrielle.freville.mareu1.MeetingListTests;

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.ViewPagerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static gabrielle.freville.mareu1.utils.RecyclerViewItemCountAssertion.withItemCount;

import gabrielle.freville.mareu1.R;
import gabrielle.freville.mareu1.api.DependencyInjection;
import gabrielle.freville.mareu1.api.MeetingApiService;
import gabrielle.freville.mareu1.ui.MainActivity;
import gabrielle.freville.mareu1.utils.DeleteViewAction;
import gabrielle.freville.mareu1.utils.SwipeUpAction;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTests {

    private static int ITEM_COUNT = 9;
    public MeetingApiService apiService;

    @Rule
    public ActivityScenarioRule scenarioRule = new ActivityScenarioRule(MainActivity.class);

    @Before
    public void setup(){
        ActivityScenario rule = scenarioRule.getScenario();
        assertNotNull(rule);
        apiService = DependencyInjection.getMeetingsApiService();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("gabrielle.freville.mareu1", appContext.getPackageName());
    }

    @Test
    public void meetingList_shouldNotBeEmpty(){
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingList_deleteAction_shouldRemoveAnItem(){
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview)).check(withItemCount(ITEM_COUNT));
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview))
                .check(withItemCount(ITEM_COUNT - 1));
    }

    @Test
    public void meetingList_swipeUp(){
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview)).check(withItemCount(ITEM_COUNT));
        onView(ViewMatchers.withId(R.id.activity_main_recyclerview)).perform(ViewActions.swipeUp());
    }

    @Test
    public void clickOnAddMeetingButton_shouldShowAddMeetingActivity(){
        onView(withId(R.id.button_add_meeting)).perform(click());
        onView(withId(R.id.add_meeting_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnFilterAction_shouldShowDialogFragment(){
        onView(withId(R.id.filter_action)).perform(click());
        onView(withId(R.id.dialog_fragment)).check(matches(isDisplayed()));
    }
}