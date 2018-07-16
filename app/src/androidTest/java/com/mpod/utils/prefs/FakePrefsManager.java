package com.mpod.utils.prefs;

import javax.inject.Inject;

/**
 * Created by xnorcode on 16/07/2018.
 */
public class FakePrefsManager implements PreferenceHelper {


    @Inject
    public FakePrefsManager() {
    }


    /**
     * Fake save of the last search
     *
     * @param search The last input search
     */
    @Override
    public void setLastSearch(String search) {

    }


    /**
     * Always retrieve an empty fake last search input
     *
     * @return the last search input
     */
    @Override
    public String getLastSearch() {
        return "";
    }
}