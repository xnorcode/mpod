package com.mpod.data.source;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.mpod.data.source.local.ArtistDao;
import com.mpod.data.source.local.MpodDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xnorcode on 13/07/2018.
 * <p>
 * This module is used by Dagger to inject the required arguments into the ArtistsRepository.class
 */
@Module
public class ArtistRepositoryModule {

    @Singleton
    @Provides
    ArtistDao provideArtistDao(MpodDatabase db) {
        return db.getArtistDao();
    }

    @Singleton
    @Provides
    MpodDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), MpodDatabase.class, "Artists.db")
                .build();
    }
}
