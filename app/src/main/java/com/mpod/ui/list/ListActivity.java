package com.mpod.ui.list;

import android.content.Intent;
import android.os.Bundle;

import com.mpod.R;
import com.mpod.utils.AppConstants;

import dagger.android.support.DaggerAppCompatActivity;

public class ListActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        // remove activity's background theme
        getWindow().setBackgroundDrawable(null);

        // add fragment to activity
        ListFragment fragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment_container);
        if (fragment == null) {
            fragment = new ListFragment();

            // get extra from intent and add in fragment
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra(AppConstants.ARTIST_NAME)) {
                Bundle args = new Bundle();
                args.putString(AppConstants.ARTIST_NAME, intent.getStringExtra(AppConstants.ARTIST_NAME));
                fragment.setArguments(args);
            }

            getSupportFragmentManager().beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }
}
