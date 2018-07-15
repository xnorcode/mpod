package com.mpod.ui.list;

import com.mpod.data.Artist;
import com.mpod.ui.base.BasePresenter;
import com.mpod.ui.base.BaseView;

import java.util.List;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface ListContract {

    interface View extends BaseView<Presenter> {


        /**
         * Show all artists
         *
         * @param artists the list of artists
         */
        void showArtists(List<Artist> artists);


        /**
         * Go to details activity and desplay more details of an artist
         *
         * @param mbid The artist mbid
         */
        void showArtistDetails(String mbid);


        /**
         * Display any errors occured
         *
         * @param msg The error message
         */
        void showError(String msg);
    }


    interface Presenter extends BasePresenter<View> {


        /**
         * search for an artist in last.fm
         *
         * @param mbid The mbid of the artist
         */
        void searchArtist(String mbid);
    }
}
