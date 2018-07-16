package com.mpod.utils.prefs;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xnorcode on 16/07/2018.
 */
@Module
public class PrefsModule {

    @Singleton
    @Provides
    PreferenceHelper providesPrefsManager(Application context) {
        return new FakePrefsManager();
    }
}
