package com.icow.main.base.domain.executor;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by hangnadi on 2/13/17.
 */
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
