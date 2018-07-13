package com.mpod.di;

import android.app.Application;

import com.mpod.MpodApplication;
import com.mpod.data.source.ArtistRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Singleton
@Component(modules = {ArtistRepositoryModule.class,
        ApplicationModule.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<MpodApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
