package com.mpod.di;

import android.app.Application;

import com.mpod.MpodApplication;
import com.mpod.data.source.ArtistRepositoryModule;
import com.mpod.ui.splash.SplashModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Singleton
@Component(modules = {ArtistRepositoryModule.class,
        ApplicationModule.class,
        SplashModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MpodApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
