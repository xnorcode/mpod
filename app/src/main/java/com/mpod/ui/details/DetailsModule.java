package com.mpod.ui.details;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 16/07/2018.
 */
@Module
public abstract class DetailsModule {

    @ContributesAndroidInjector
    abstract DetailsFragment providesDetailsFragment();

    @Binds
    abstract DetailsContract.Presenter providesPresenter(DetailsPresenter presenter);
}
