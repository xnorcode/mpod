package com.mpod.utils.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

/**
 * Created by xnorcode on 16/07/2018.
 */
public class PrefsManager implements PreferenceHelper {

    private static final String LAST_SEARCH_INPUT_PREFS_KEY = "LAST_SEARCH_INPUT_PREFS_KEY";

    private SharedPreferences mPrefs;


    @Inject
    public PrefsManager(Context context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }


    /**
     * Save the last search input
     *
     * @param search The last input search
     */
    @Override
    public void setLastSearch(String search) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(LAST_SEARCH_INPUT_PREFS_KEY, search);
        editor.commit();
    }


    /**
     * Retrive the last search input
     *
     * @return the last search input
     */
    @Override
    public String getLastSearch() {
        return mPrefs.getString(LAST_SEARCH_INPUT_PREFS_KEY, "");
    }
}
