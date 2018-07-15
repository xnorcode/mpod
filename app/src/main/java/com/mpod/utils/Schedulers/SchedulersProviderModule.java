package com.mpod.utils.Schedulers;

import dagger.Binds;
import dagger.Module;

/**
 * Created by xnorcode on 15/07/2018.
 */
@Module
public abstract class SchedulersProviderModule {

    @Binds
    abstract BaseSchedulersProvider provideSchedulersProvider(SchedulersProvider schedulersProvider);
}
