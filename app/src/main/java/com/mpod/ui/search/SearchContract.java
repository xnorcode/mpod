package com.mpod.ui.search;

import com.mpod.ui.base.BasePresenter;
import com.mpod.ui.base.BaseView;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface SearchContract {

    interface View extends BaseView<Presenter> {


        /**
         * proceed to list activity
         */
        void startListActivity(String artistNme);


        /**
         * Show error
         *
         * @param msg The error message to be shown
         */
        void showError(String msg);
    }

    interface Presenter extends BasePresenter<View> {


        /**
         * start search repositories for the given artist
         *
         * @param artistName The artist name to search for
         */
        void startSearch(String artistName);
    }
}
