package com.mpod.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mpod.R;
import com.mpod.utils.AppConstants;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class DetailsActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        // remove activity's background theme
        getWindow().setBackgroundDrawable(null);

        // add fragment to activity
        DetailsFragment fragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_fragment_container);
        if (fragment == null) {
            fragment = new DetailsFragment();

            // get extra from intent and add in fragment
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra(AppConstants.ARTIST_MBID)) {
                Bundle args = new Bundle();
                args.putString(AppConstants.ARTIST_MBID, intent.getStringExtra(AppConstants.ARTIST_MBID));
                fragment.setArguments(args);
            }

            getSupportFragmentManager().beginTransaction().add(R.id.details_fragment_container, fragment).commit();
        }
    }
}
