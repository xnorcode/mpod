package com.mpod.ui.splash;

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
public class SplashPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SplashContract.View mView;

    private SplashPresenter mPresenter;

    @Before
    public void setup() {

        // init presenter
        mPresenter = new SplashPresenter();

        // set mock view
        mPresenter.setView(mView);
    }

    @Test
    public void proceedToNextActivity() {

        // call proceed method
        mPresenter.proceed();

        // verify that start next activity method was called only once
        Mockito.verify(mView).startNextActivity();
    }
}
