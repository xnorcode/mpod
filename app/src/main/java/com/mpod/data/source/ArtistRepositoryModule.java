package com.mpod.data.source;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.mpod.R;
import com.mpod.data.source.local.ArtistDao;
import com.mpod.data.source.local.ArtistLocalDataSource;
import com.mpod.data.source.local.DbHelper;
import com.mpod.data.source.local.MpodDatabase;
import com.mpod.data.source.remote.ApiHelper;
import com.mpod.data.source.remote.ArtistRemoteDataSource;
import com.mpod.data.source.remote.LastfmApiKey;

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
    ApiHelper provideArtistRemoteDataSource(String apiKey) {
        return new ArtistRemoteDataSource(apiKey);
    }

    @Singleton
    @Provides
    DbHelper provideArtistLocalDataSource(ArtistDao dao) {
        return new ArtistLocalDataSource(dao);
    }

    @Singleton
    @Provides
    @LastfmApiKey
    String provideApiKey(Application context) {
        return context.getApplicationContext().getString(R.string.lastfm_api_key);
    }

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
