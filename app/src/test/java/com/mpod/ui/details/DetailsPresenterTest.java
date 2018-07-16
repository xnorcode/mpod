package com.mpod.ui.details;

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

import io.reactivex.Flowable;

/**
 * Created by xnorcode on 16/07/2018.
 */
public class DetailsPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private DetailsContract.View mView;

    @Mock
    private ArtistRepository mArtistRepository;

    private Artist mArtist;

    private DetailsPresenter mPresenter;

    @Before
    public void setup() {

        // init presenter with test schedulers and mock repository
        mPresenter = new DetailsPresenter(mArtistRepository, TestSchedulersProvider.getInstance());

        // set mock view
        mPresenter.setView(mView);

        // init artist and list
        mArtist = new Artist("mbid", "name", "profile_url");
        mArtist.setListeners("10");
    }

    @Test
    public void getArtistInfo_showArtistInfo() {

        // set data available in artist repository
        Mockito.when(mArtistRepository.getArtistInfo("mbid")).thenReturn(Flowable.just(mArtist));

        // start search
        mPresenter.getArtistInfo("mbid");

        // verify start list activity is triggered
        Mockito.verify(mView).showArtistDetails(mArtist);
    }

    @Test
    public void getArtistInfo_showError() {

        // set data not available in artist repository
        Mockito.when(mArtistRepository.getArtistInfo("mbid")).thenReturn(Flowable.error(new Exception("error msg")));

        // start search
        mPresenter.getArtistInfo("mbid");

        // verify show artists is not called
        Mockito.verify(mView, Mockito.times(0)).showArtistDetails(mArtist);

        // verify show error is called
        Mockito.verify(mView).showError("error msg");
    }
}
