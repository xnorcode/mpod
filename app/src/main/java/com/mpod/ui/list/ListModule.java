package com.mpod.ui.list;

import dagger.Binds;
import dagger.Module;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class ListModule {

    @Binds
    abstract ListFragment providesListFragment();

    @Binds
    abstract ListContract.Presenter provideListPresenter(ListPresenter presenter);
}
