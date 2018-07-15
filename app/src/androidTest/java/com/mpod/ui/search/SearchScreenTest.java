package com.mpod.ui.search;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.R;

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
        // check hint correct
        Espresso.onView(ViewMatchers.withId(R.id.search_box))
                // check hint is correct
                .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_text)))
                .perform(ViewActions.replaceText("Cher"), ViewActions.pressImeActionButton())
                .check(ViewAssertions.matches(ViewMatchers.withText("Cher")));
    }

}
