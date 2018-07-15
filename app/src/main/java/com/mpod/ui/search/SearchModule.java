package com.mpod.ui.search;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class SearchModule {

    @ContributesAndroidInjector
    abstract SearchFragment providesSearchFragment();

    @Binds
    abstract SearchContract.Presenter providesSearchPresenter(SearchPresenter presenter);
}
