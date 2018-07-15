package com.mpod.ui.search;

import javax.inject.Inject;

/**
 * Created by xnorcode on 15/07/2018.
 * <p>
 * Search Presenter should have additional logic to validate input
 */
public class SearchPresenter implements SearchContract.Presenter {


    private SearchContract.View mView;

    @Inject
    public SearchPresenter() {
    }


    @Override
    public void startSearch(String artistName) {
        mView.startListActivity(artistName);
    }

    @Override
    public void setView(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
