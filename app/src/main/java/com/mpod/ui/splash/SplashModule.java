package com.mpod.ui.splash;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class SplashModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeActivityInjector();

    @Binds
    abstract SplashContract.Presenter procidesSplashPresenter(SplashPresenter presenter);
}
