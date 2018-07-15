package com.mpod.ui.splash;

import dagger.Binds;
import dagger.Module;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class SplashModule {

    @Binds
    abstract SplashContract.Presenter procidesSplashPresenter(SplashPresenter presenter);
}
