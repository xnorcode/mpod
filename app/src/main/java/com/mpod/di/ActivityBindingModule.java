package com.mpod.di;

import com.mpod.ui.splash.SplashActivity;
import com.mpod.ui.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity provideSplashActivity();
}
