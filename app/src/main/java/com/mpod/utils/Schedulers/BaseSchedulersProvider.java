package com.mpod.utils.Schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by xnorcode on 15/07/2018.
 */
public interface BaseSchedulersProvider {


    /**
     * Retrieve background thread scheduler
     * @return
     */
    @NonNull
    Scheduler io();


    /**
     * Retrieve main thread scheduler
     * @return
     */
    @NonNull
    Scheduler ui();
}
