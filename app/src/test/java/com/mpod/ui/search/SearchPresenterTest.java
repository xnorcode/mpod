package com.mpod.ui.search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SearchPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SearchContract.View mView;

    private SearchPresenter mPresenter;

    @Before
    public void setup() {

        // init presenter
        mPresenter = new SearchPresenter();

        // set mock view
        mPresenter.setView(mView);
    }

    @Test
    public void startSearchArtist_StartNextActivity() {

        // start search
        mPresenter.startSearch("name");

        // verify start list activity method is triggered
        Mockito.verify(mView).startListActivity("name");
    }
}