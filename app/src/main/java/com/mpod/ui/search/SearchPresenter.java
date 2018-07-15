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
        if (isValid(artistName))
            mView.startListActivity(artistName);
        else
            mView.showError("Invalid input please try again...");
    }

    @Override
    public void setView(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }


    /**
     * Validate input
     */
    private boolean isValid(String input) {
        if (input == null || input.isEmpty()) return false;
        return true;
    }
}
