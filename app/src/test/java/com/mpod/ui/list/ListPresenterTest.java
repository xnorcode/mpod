package com.mpod.ui.list;

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
public class ListPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ListContract.View mView;

    @Mock
    private ArtistRepository mArtistRepository;

    private Artist mArtist;
    private List<Artist> mArtists;

    private ListPresenter mPresenter;

    @Before
    public void setup() {

        // init presenter with test schedulers and mock repository
        mPresenter = new ListPresenter(mArtistRepository, TestSchedulersProvider.getInstance());

        // set mock view
        mPresenter.setView(mView);

        // init artist and list
        mArtist = new Artist("mbid", "name", "profile_url");
        mArtists = new ArrayList<>();
        mArtists.add(mArtist);
    }

    @Test
    public void searchArtist_showArtists() {

        // set data available in artist repository
        Mockito.when(mArtistRepository.searchArtist("name")).thenReturn(Flowable.just(mArtists));

        // start search
        mPresenter.searchArtist("name");

        // verify start list activity is triggered
        Mockito.verify(mView).showArtists(mArtists);
    }

    @Test
    public void searchArtist_showError() {

        // set data not available in artist repository
        Mockito.when(mArtistRepository.searchArtist("name")).thenReturn(Flowable.error(new Exception("error msg")));

        // start search
        mPresenter.searchArtist("name");

        // verify show artists is not called
        Mockito.verify(mView, Mockito.times(0)).showArtists(mArtists);

        // verify show error is called
        Mockito.verify(mView).showError("error msg");
    }
}
