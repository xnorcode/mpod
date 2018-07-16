package com.mpod.ui.details;

import android.content.Context;
import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.MpodApplication;
import com.mpod.utils.AppConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by xnorcode on 16/07/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailsScreenTest {

    @Rule
    public ActivityTestRule<DetailsActivity> mDetailsActivityTestRule =
            new ActivityTestRule<>(DetailsActivity.class);

    @Before
    public void clearDb() {
        ((MpodApplication) mDetailsActivityTestRule.getActivity().getApplicationContext())
                .getArtistRepository().deleteAllArtists();
    }

    @Test
    public void getArtistInfo_showArtistInfo() {

        // launching activity with input data
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putExtra(AppConstants.ARTIST_MBID, "mbid");
        mDetailsActivityTestRule.launchActivity(intent);

        // verify artist data is shown
        onView(withText("name")).check(matches(isDisplayed()));

        // close activity
        mDetailsActivityTestRule.finishActivity();
    }

    @Test
    public void getArtistInfo_showError() {

        // launching activity with input data
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putExtra(AppConstants.ARTIST_MBID, "");
        mDetailsActivityTestRule.launchActivity(intent);

        // verify toast message is displayed
        onView(withText("Could not download Artist info..."))
                .inRoot(withDecorView(not(mDetailsActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        // close activity
        mDetailsActivityTestRule.finishActivity();
    }
}
