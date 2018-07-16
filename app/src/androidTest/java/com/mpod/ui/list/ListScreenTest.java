package com.mpod.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.MpodApplication;
import com.mpod.R;
import com.mpod.utils.AppConstants;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by xnorcode on 15/07/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListScreenTest {


    @Rule
    public ActivityTestRule<ListActivity> mListActivityTestRule =
            new ActivityTestRule<>(ListActivity.class);

    @Before
    public void clearDb() {
        ((MpodApplication) mListActivityTestRule.getActivity().getApplicationContext())
                .getArtistRepository().deleteAllArtists();
    }

    @Test
    public void testB_searchArtist_showArtists() {

        // launching activity with input data
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, ListActivity.class);
        intent.putExtra(AppConstants.ARTIST_NAME, "name");
        mListActivityTestRule.launchActivity(intent);

        // verify list is enabled
        onView(withId(R.id.list_recyclerView)).check(matches(isDisplayed()));

        // verify list items are shown
        onView(withText("name")).check(matches(isDisplayed()));

        // close activity
        mListActivityTestRule.finishActivity();
    }

    @Test
    public void testA_searchArtist_showError() {

        // launching activity with input data that produce an error
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, ListActivity.class);
        intent.putExtra(AppConstants.ARTIST_NAME, "");
        mListActivityTestRule.launchActivity(intent);

        // verify list is enabled
        onView(withId(R.id.list_recyclerView)).check(matches(isDisplayed()));

        // verify toast message is displayed
        onView(withText("Could not download artists..."))
                .inRoot(withDecorView(not(mListActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        // close activity
        mListActivityTestRule.finishActivity();
    }

    @Test
    public void testC_searchArtist_showDetails() {

        // launching activity with input data
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, ListActivity.class);
        intent.putExtra(AppConstants.ARTIST_NAME, "name");
        mListActivityTestRule.launchActivity(intent);

        // verify list is enabled
        onView(withId(R.id.list_recyclerView)).check(matches(isDisplayed()));

        // click on item that has a children item with text "name"
        onView(withChild(withText("name"))).perform(click());

        // verify details activity intent has been sent with extra data
        intended(allOf(
                toPackage("com.mpod.ui.details"),
                hasExtraWithKey(AppConstants.ARTIST_MBID)));

        // close activity
        mListActivityTestRule.finishActivity();
    }
}
