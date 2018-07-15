package com.mpod.data.source;

import com.mpod.data.Artist;
import com.mpod.data.source.local.DbHelper;
import com.mpod.data.source.remote.ApiHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class ArtistRepositoryTest {

    // invokes MockitoAnnotations.initMocks()
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    private Artist mArtist;
    private List<Artist> mArtists;

    @Mock
    private ApiHelper mArtistRemoteDataSource;

    @Mock
    private DbHelper mArtistLocalDataSource;

    @InjectMocks
    private ArtistRepository mArtistRepository;


    @Before
    public void setup() {
        // init artist and list
        mArtist = new Artist("mbid", "name", "profile_url");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @Test
    public void searchArtist_FromRemoteSource() {
        // make data in remote data source available
        Mockito.when(mArtistRemoteDataSource.searchArtist("name")).thenReturn(mArtists);

        // make data in local data source not available
        Mockito.when(mArtistLocalDataSource.getArtists()).thenReturn(Collections.emptyList());

        // call method and subscribe
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        mArtistRepository.searchArtist("name").subscribe(testSubscriber);

        // verify that both methods invoked only once
        Mockito.verify(mArtistLocalDataSource).getArtists();
        Mockito.verify(mArtistRemoteDataSource).searchArtist("name");

        // verify cache is valid
        Assert.assertFalse(mArtistRepository.mCacheInvalid);

        // check output data
        testSubscriber.assertValue(mArtists);
    }

    @Test
    public void searchArtist_RemoteDataSourceFails() {
        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.searchArtist("name")).thenReturn(Collections.emptyList());

        // make data in local data source not available
        Mockito.when(mArtistLocalDataSource.getArtists()).thenReturn(Collections.emptyList());

        // call method and subscribe
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        mArtistRepository.searchArtist("name").subscribe(testSubscriber);

        // verify that both methods invoked only once
        Mockito.verify(mArtistLocalDataSource).getArtists();
        Mockito.verify(mArtistRemoteDataSource).searchArtist("name");

        // check output data
        testSubscriber.assertError(Exception.class);
    }


    @Test
    public void searchArtist_FromLocalSource() {
        // make data in local data source available
        Mockito.when(mArtistLocalDataSource.getArtists()).thenReturn(mArtists);

        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.searchArtist("name")).thenReturn(Collections.emptyList());

        // call method and subscribe
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        mArtistRepository.searchArtist("name").subscribe(testSubscriber);

        // verify that local method invoked only once and remote never invoked
        Mockito.verify(mArtistLocalDataSource).getArtists();
        Mockito.verify(mArtistRemoteDataSource, Mockito.times(0)).searchArtist("name");

        // verify cache is valid
        Assert.assertFalse(mArtistRepository.mCacheInvalid);

        // check output data
        testSubscriber.assertValue(mArtists);
    }

    @Test
    public void searchArtist_CheckCache() {
        // make data in local data source available
        Mockito.when(mArtistLocalDataSource.getArtists()).thenReturn(mArtists);

        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.searchArtist("name")).thenReturn(Collections.emptyList());

        // call method and subscribe
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        mArtistRepository.searchArtist("name").subscribe(testSubscriber);

        // check output data
        Mockito.verify(mArtistLocalDataSource).getArtists();
        testSubscriber.assertValue(mArtists);

        // verify cache data values
        List<Artist> actual = new ArrayList<>(mArtistRepository.mCachedArtists.values());
        Assert.assertEquals(actual, mArtists);
    }

    @Test
    public void getArtistInfo_FromRemoteSource() {
        // set info in artist
        mArtist.setBioSummary("bioSummary");

        // make data in remote data source available
        Mockito.when(mArtistRemoteDataSource.getArtistInfo("mbid")).thenReturn(mArtist);

        // make data in local data source not available
        Mockito.when(mArtistLocalDataSource.getArtistInfo("mbid")).thenReturn(null);

        //call method and subscribe
        TestSubscriber<Artist> testSubscriber = new TestSubscriber<>();
        mArtistRepository.getArtistInfo("mbid").subscribe(testSubscriber);

        // verify that remote and local methods called only once
        Mockito.verify(mArtistLocalDataSource).getArtistInfo("mbid");
        Mockito.verify(mArtistRemoteDataSource).getArtistInfo("mbid");

        // verify cache is valid
        Assert.assertFalse(mArtistRepository.mCacheInvalid);

        // check data output
        testSubscriber.assertValue(mArtist);
    }

    @Test
    public void getArtistInfo_FromLocalSource() {
        // set info in artist
        mArtist.setBioSummary("bioSummary");

        // make data in local data source available
        Mockito.when(mArtistLocalDataSource.getArtistInfo("mbid")).thenReturn(mArtist);

        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.getArtistInfo("mbid")).thenReturn(null);

        //call method and subscribe
        TestSubscriber<Artist> testSubscriber = new TestSubscriber<>();
        mArtistRepository.getArtistInfo("mbid").subscribe(testSubscriber);

        // verify that remote and local methods called only once
        Mockito.verify(mArtistLocalDataSource).getArtistInfo("mbid");
        Mockito.verify(mArtistRemoteDataSource, Mockito.times(0)).getArtistInfo("mbid");

        // verify cache is valid
        Assert.assertFalse(mArtistRepository.mCacheInvalid);

        // check data output
        testSubscriber.assertValue(mArtist);
    }

    @Test
    public void getArtistInfo_CheckCache() {
        // set info in artist
        mArtist.setBioSummary("bioSummary");

        // init cache
        mArtistRepository.mCachedArtists = new LinkedHashMap<>();

        // make data in local data source available
        Mockito.when(mArtistLocalDataSource.getArtistInfo("mbid")).thenReturn(mArtist);

        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.getArtistInfo("mbid")).thenReturn(null);

        // call method and subscribe
        TestSubscriber<Artist> testSubscriber = new TestSubscriber<>();
        mArtistRepository.getArtistInfo("mbid").subscribe(testSubscriber);

        // check output data
        Mockito.verify(mArtistLocalDataSource).getArtistInfo("mbid");
        testSubscriber.assertValue(mArtist);

        // verify cache data values
        Artist actual = mArtistRepository.mCachedArtists.get("mbid");
        Assert.assertEquals(actual, mArtist);
    }

    @Test
    public void getArtistInfo_RemoteDataSourceFails() {
        // make data in remote data source not available
        Mockito.when(mArtistRemoteDataSource.getArtistInfo("mbid")).thenReturn(null);

        // make data in local data source not available
        Mockito.when(mArtistLocalDataSource.getArtistInfo("mbid")).thenReturn(null);

        // call method and subscribe
        TestSubscriber<Artist> testSubscriber = new TestSubscriber<>();
        mArtistRepository.getArtistInfo("mbid").subscribe(testSubscriber);

        // verify that both methods invoked only once
        Mockito.verify(mArtistLocalDataSource).getArtistInfo("mbid");
        Mockito.verify(mArtistRemoteDataSource).getArtistInfo("mbid");

        // check output data
        testSubscriber.assertError(Exception.class);
    }
}
