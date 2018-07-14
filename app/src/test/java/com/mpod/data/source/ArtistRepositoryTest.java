package com.mpod.data.source;

import com.mpod.data.Artist;
import com.mpod.data.source.local.DbHelper;
import com.mpod.data.source.remote.ApiHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;

/**
 * Created by xnorcode on 13/07/2018.
 */
public class ArtistRepositoryTest {


    private Artist mArtist;
    private List<Artist> mArtists;

    private ArtistRepository mArtistRepository;

    private TestSubscriber<List<Artist>> mArtistsSubscriber;

    private TestSubscriber<Artist> mArtistInfoSubscriber;

    @Mock
    private ApiHelper mArtistRemoteDataSource;

    @Mock
    private DbHelper mArtistLocalDataSource;


    @Before
    public void setup() {
        // init mock injection
        MockitoAnnotations.initMocks(this);

        // init repository
        mArtistRepository = new ArtistRepository(mArtistLocalDataSource, mArtistRemoteDataSource);

        // init test subscribers
        mArtistsSubscriber = new TestSubscriber<>();
        mArtistInfoSubscriber = new TestSubscriber<>();

        // init artist and list
        mArtist = new Artist("mbid", "name", "profile_url");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @Test
    public void getArtistsFromRemoteSource() {

    }


}
