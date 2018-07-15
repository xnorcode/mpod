package com.mpod.utils.Schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface BaseSchedulersProvider {

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
