package com.mpod.ui.search;

import com.mpod.data.Artist;
import com.mpod.data.source.ArtistRepository;
import com.mpod.utils.Schedulers.TestSchedulersProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SearchPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SearchContract.View mView;

    @Mock
    private ArtistRepository mArtistRepository;

    private Artist mArtist;
    private List<Artist> mArtists;

    private SearchPresenter mPresenter;

    @Before
    public void setup() {

        // init presenter with test schedulers and mock repository
        mPresenter = new SearchPresenter(mArtistRepository, TestSchedulersProvider.getInstance());

        // set mock view
        mPresenter.setView(mView);

        // init artist and list
        mArtist = new Artist("mbid", "name", "profile_url");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @Test
    public void startSearchArtist() {

        // set data available in artist repository
        Mockito.when(mArtistRepository.searchArtist("name")).thenReturn(Flowable.just(mArtists));

        // start search
        mPresenter.startSearch("name");

        Mockito.verify(mView).startListActivity("name");
    }
}
