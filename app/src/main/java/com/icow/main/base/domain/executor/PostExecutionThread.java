package com.icow.main.base.domain.executor;

import rx.Scheduler;

/**
 * Created by hangnadi on 2/10/17.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
