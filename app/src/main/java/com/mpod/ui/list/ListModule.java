package com.mpod.ui.list;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class ListModule {

    @ContributesAndroidInjector
    abstract ListFragment providesListFragment();

    @Binds
    abstract ListContract.Presenter provideListPresenter(ListPresenter presenter);
}
