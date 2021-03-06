package com.mpod.di;

import com.mpod.ui.details.DetailsActivity;
import com.mpod.ui.details.DetailsModule;
import com.mpod.ui.list.ListActivity;
import com.mpod.ui.list.ListModule;
import com.mpod.ui.search.SearchActivity;
import com.mpod.ui.search.SearchModule;
import com.mpod.ui.splash.SplashActivity;
import com.mpod.ui.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity provideSplashActivity();

    @ContributesAndroidInjector(modules = SearchModule.class)
    abstract SearchActivity provideSearchActivity();

    @ContributesAndroidInjector(modules = ListModule.class)
    abstract ListActivity provideListActivity();

    @ContributesAndroidInjector(modules = DetailsModule.class)
    abstract DetailsActivity provideDetailsActivity();
}
