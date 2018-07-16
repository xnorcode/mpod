package com.mpod.data.source.remote.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mpod.data.Artist;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnorcode on 13/07/2018.
 */
@RunWith(AndroidJUnit4.class)
public class JsonHelperTest {

    private String mSearchArtistResponseData;
    private String mGetArtistInfoResponseData;

    private Artist mArtist;
    private List<Artist> mArtists;

    @Before
    public void setup() throws IOException {
        // init fake api response data
        mSearchArtistResponseData = loadFakeData("artist_search.json");
        mGetArtistInfoResponseData = loadFakeData("artist_getinfo.json");

        // init artist and list
        mArtist = new Artist("bfcc6d75-a6a5-4bc6-8282-47aec8531818", "Cher", "https://www.last.fm/music/Cher");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @Test
    public void extractArtists() throws JSONException {

        // get data from json
        List<Artist> actual = JsonHelper.extractArtists(mSearchArtistResponseData);

        // check data size
        Assert.assertNotEquals(0, actual.size());

        // check artist
        Assert.assertEquals(mArtist, actual.get(0));
    }

    @Test
    public void extractArtistInfo() throws JSONException {

        // get artist info from json
        Artist actual = JsonHelper.extractArtistInfo(mGetArtistInfoResponseData);

        // check if null
        Assert.assertNotNull(actual);

        // check artist
        Assert.assertEquals(mArtist, actual);

        // check listeners
        Assert.assertEquals("1055352", actual.getListeners());
    }

    private String loadFakeData(String fileName) throws IOException {

        // connect to json file in assets
        InputStream is = InstrumentationRegistry.getContext().getResources().getAssets().open(fileName);

        // read the file
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        // close stream
        is.close();
        return new String(buffer, "UTF-8");
    }
}
