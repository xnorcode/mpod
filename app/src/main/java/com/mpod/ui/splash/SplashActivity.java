package com.mpod.ui.splash;

import android.content.Intent;

import com.mpod.ui.search.SearchActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class SplashActivity extends DaggerAppCompatActivity implements SplashContract.View {


    @Inject
    SplashContract.Presenter mPresenter;


    @Override
    protected void onResume() {
        super.onResume();

        // set view
        mPresenter.setView(this);

        // we usually check internet connection or perform
        // any other tasks here before proceeding into the app
        mPresenter.proceed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Override
    public void startNextActivity() {
        startActivity(new Intent(this, SearchActivity.class));
        finish();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        // used for manual injection of the presenter
    }
}
