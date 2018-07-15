package com.mpod.ui.search;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by xnorcode on 15/07/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchScreenTest {

    @Rule
    public ActivityTestRule<SearchActivity> mSearchActivityTestRule =
            new ActivityTestRule<>(SearchActivity.class);


    @Test
    public void searchArtist_SearchBoxInput() {

        // verify search box is displayed
        Espresso.onView(ViewMatchers.withId(R.id.search_box))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                // check hint is correct
                .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_text)));

        // type and search for artist
        Espresso.onView(ViewMatchers.withId(R.id.search_box))
                .perform(ViewActions.replaceText("Cher"), ViewActions.pressImeActionButton());

        // verify that list activity is shown
        Espresso.onView(ViewMatchers.withId(R.id.list_fragment_container))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void searchArtist_SearchBoxInvalidInput() {

        // verify search box is displayed
        Espresso.onView(ViewMatchers.withId(R.id.search_box))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // type empty in the search box
        Espresso.onView(ViewMatchers.withId(R.id.search_box))
                .perform(ViewActions.replaceText(""), ViewActions.pressImeActionButton());

        // verify toast message is displayed
        Espresso.onView(ViewMatchers.withText("Invalid input please try again..."))
                .inRoot(RootMatchers.withDecorView(Matchers.not(mSearchActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
