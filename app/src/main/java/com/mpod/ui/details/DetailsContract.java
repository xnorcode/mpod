package com.mpod.ui.details;

import com.mpod.data.Artist;
import com.mpod.ui.base.BasePresenter;
import com.mpod.ui.base.BaseView;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface DetailsContract {

    interface View extends BaseView<Presenter> {


        /**
         * Show artist details on screen
         *
         * @param artist the artist
         */
        void showArtistDetails(Artist artist);


        /**
         * Display an error message
         *
         * @param msg The error message
         */
        void showError(String msg);
    }

    interface Presenter extends BasePresenter<View> {


        /**
         * Download artist info
         *
         * @param mbid the mbid of the artist
         */
        void getArtistInfo(String mbid);
    }
}
