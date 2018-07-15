package com.mpod.ui.splash;

import javax.inject.Inject;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SplashActivityPresenter implements SplashActivityContract.Presenter {


    SplashActivityContract.View mView;


    @Inject
    public SplashActivityPresenter() {
    }


    @Override
    public void proceed() {
        mView.startNextActivity();
    }

    @Override
    public void setView(SplashActivityContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
