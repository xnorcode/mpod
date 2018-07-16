package com.mpod.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.data.Artist;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ArtistLocalDataSourceTest {

    private DbHelper mLocalDataSource;

    private MpodDatabase mDatabase;

    private Artist mArtist;
    private List<Artist> mArtists;

    @Before
    public void setup() {

        // init db
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                MpodDatabase.class).build();

        // create ArtistDao
        ArtistDao dao = mDatabase.getArtistDao();

        // create ArtistLocalDataSource
        mLocalDataSource = new ArtistLocalDataSource(dao);

        // init artist and list
        mArtist = new Artist("mbid", "name", "lastfmprofileurl");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @After
    public void clearDb() {
        mDatabase.getArtistDao().deleteArtists();
        mDatabase.close();
    }

    @Test
    public void saveAndGetAllArtists() {

        // save artists
        mLocalDataSource.saveArtists(mArtists);

        // get all
        List<Artist> actual = mLocalDataSource.getArtists();

        // check actual value size
        Assert.assertEquals(1, actual.size());

        // check artist
        Assert.assertEquals(mArtist, actual.get(0));
    }

    @Test
    public void saveAndGetArtistById() {

        // save artists
        mLocalDataSource.saveArtists(mArtists);

        // get artist by mbid
        Artist actual = mLocalDataSource.getArtistInfo("mbid");

        // check artist
        Assert.assertEquals(mArtist, actual);
    }

    @Test
    public void saveAndUpdateArtist() {

        // save artists
        mLocalDataSource.saveArtists(mArtists);

        // set summary
        mArtist.setBioSummary("bioSummary");

        // update artist
        mLocalDataSource.updateArtist(mArtist);

        // get artist
        Artist actual = mLocalDataSource.getArtistInfo("mbid");

        // check artist
        Assert.assertEquals(mArtist, actual);

        // check summary
        Assert.assertEquals(mArtist.getBioSummary(), actual.getBioSummary());
    }

    @Test
    public void saveAndDeleteAllArtists() {

        // save artists
        mLocalDataSource.saveArtists(mArtists);

        // delete all artists from db
        mLocalDataSource.deleteAllArtists();

        // get all artists
        List<Artist> actual = mLocalDataSource.getArtists();

        // check actual data size
        Assert.assertEquals(actual.size(), 0);
    }
}
