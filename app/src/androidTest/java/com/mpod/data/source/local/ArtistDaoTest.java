package com.mpod.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.data.Artist;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 * <p>
 * Testing local database
 */
@RunWith(AndroidJUnit4.class)
public class ArtistDaoTest {


    private MpodDatabase mDatabase;

    private Artist mArtist;
    private List<Artist> mArtists;


    @Before
    public void initDbAndData() {
        // init db
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                MpodDatabase.class).build();

        // init artist and list
        mArtist = new Artist("mbid", "name", "lastfmprofileurl");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void insertAndGetAllArtists() {

        // insert artists
        mDatabase.getArtistDao().insertArtists(mArtists);

        // get artists
        List<Artist> actualValues = mDatabase.getArtistDao().getArtists();

        // check list size
        Assert.assertEquals(1, actualValues.size());

        // check the actual values return from db
        Assert.assertEquals(mArtist, actualValues.get(0));
    }

    @Test
    public void insertAndGetArtistById() {

        // insert artists
        mDatabase.getArtistDao().insertArtists(mArtists);

        // get artist by mbid
        Artist actual = mDatabase.getArtistDao().getArtistById("mbid");

        // check actual values return by db
        Assert.assertEquals(actual, mArtist);
    }

    @Test
    public void insertAndUpdateArtist() {

        // insert artists
        mDatabase.getArtistDao().insertArtists(mArtists);

        // set artist summary
        mArtist.setBioSummary("bioSummary");

        // update artist
        mDatabase.getArtistDao().updateArtist(mArtist);

        // get artist by mbid
        Artist actual = mDatabase.getArtistDao().getArtistById("mbid");

        // check actual values return by db
        Assert.assertEquals(actual, mArtist);

        // check summary
        Assert.assertEquals(actual.getBioSummary(), "bioSummary");
    }

    @Test
    public void insertAndDeleteAllArtists() {

        // insert artists
        mDatabase.getArtistDao().insertArtists(mArtists);

        // delete all artists from db
        mDatabase.getArtistDao().deleteArtists();

        // get all artists
        List<Artist> actual = mDatabase.getArtistDao().getArtists();

        // check actual data size
        Assert.assertEquals(actual.size(), 0);
    }
}
