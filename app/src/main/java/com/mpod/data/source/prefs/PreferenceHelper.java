package com.mpod.data.source.prefs;

/**
 * Created by xnorcode on 16/07/2018.
 */
public interface PreferenceHelper {


    /**
     * Save in shared preferences the last artist searched
     *
     * @param search The last input search
     */
    void setLastSearch(String search);


    /**
     * Retrive the last search input
     *
     * @return the last search input
     */
    String getLastSearch();
}
