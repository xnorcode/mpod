package com.mpod.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mpod.data.Artist;

/**
 * Created by xnorcode on 13/07/2018.
 * <p>
 * The Room database that contains the artist table
 */
@Database(entities = {Artist.class}, version = 1)
public abstract class MpodDatabase extends RoomDatabase {

    public abstract ArtistDao getArtistDao();
}
