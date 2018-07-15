package com.mpod.ui.splash;

import javax.inject.Inject;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SplashPresenter implements SplashContract.Presenter {


    SplashContract.View mView;


    @Inject
    public SplashPresenter() {
    }


    @Override
    public void proceed() {
        mView.startNextActivity();
    }

    @Override
    public void setView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
