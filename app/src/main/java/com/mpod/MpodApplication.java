package com.mpod;

import android.support.annotation.VisibleForTesting;

import com.mpod.data.source.ArtistRepository;
import com.mpod.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class MpodApplication extends DaggerApplication {

    @Inject
    ArtistRepository mArtistRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


    /**
     * This is used only in tests to delete all artists from local db
     *
     * @return
     */
    @VisibleForTesting
    public ArtistRepository getArtistRepository() {
        return mArtistRepository;
    }
}
