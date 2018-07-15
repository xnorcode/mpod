package com.mpod.utils.Schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xnorcode on 15/07/2018.
 */
public class SchedulersProvider implements BaseSchedulersProvider {

    private static SchedulersProvider INSTANCE;

    // prevent direct instantiation
    private SchedulersProvider() {
    }

    public static synchronized SchedulersProvider getInstance() {
        if (INSTANCE == null) INSTANCE = new SchedulersProvider();
        return INSTANCE;
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
