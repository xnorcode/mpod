package com.mpod.utils.Schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xnorcode on 15/07/2018.
 * <p>
 * Test schedulers for testing
 */
public class TestSchedulersProvider implements BaseSchedulersProvider {

    private static TestSchedulersProvider INSTANCE;

    // prevent direct instantiation
    private TestSchedulersProvider() {
    }

    public static synchronized TestSchedulersProvider getInstance() {
        if (INSTANCE == null) INSTANCE = new TestSchedulersProvider();
        return INSTANCE;
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
