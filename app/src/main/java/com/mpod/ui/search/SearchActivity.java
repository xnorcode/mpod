package com.mpod.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mpod.R;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class SearchActivity extends DaggerAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        // remove activity's background theme
        getWindow().setBackgroundDrawable(null);

        // add fragment to activity
        SearchFragment fragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment_container);
        if (fragment == null) {
            fragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.search_fragment_container, fragment).commit();
        }
    }


}
