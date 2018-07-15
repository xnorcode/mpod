package com.mpod.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.MpodApplication;
import com.mpod.R;
import com.mpod.utils.AppConstants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by xnorcode on 15/07/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListScreenTest {


    @Rule
    public ActivityTestRule<ListActivity> mListActivityTestRule =
            new ActivityTestRule<ListActivity>(ListActivity.class) {

                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    // clear database before each activity launch
                    ((MpodApplication) InstrumentationRegistry.getTargetContext()
                            .getApplicationContext()).getArtistRepository().deleteAllArtists();
                }

                @Override
                protected Intent getActivityIntent() {
                    // set intent with artist to search coming from search screen
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, ListActivity.class);
                    intent.putExtra(AppConstants.ARTIST_NAME, "Cher");
                    return intent;
                }
            };

    @Test
    public void searchArtist_showArtists() {

        // verify list is enabled
        Espresso.onView(ViewMatchers.withId(R.id.list_recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // verify list items are shown
        Espresso.onView(ViewMatchers.withText("name"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void searchArtist_showError() {

    }

    @Test
    public void searchArtist_showDetails() {

    }
}
