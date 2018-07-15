package com.mpod.ui.list;

import android.os.Bundle;

import com.mpod.R;

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
            getSupportFragmentManager().beginTransaction().add(R.id.list_fragment_container, fragment).commit();
        }
    }
}
