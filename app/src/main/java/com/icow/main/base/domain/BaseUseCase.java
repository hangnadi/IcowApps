package com.icow.main.base.domain;


import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by hangnadi on 2/10/17.
 */
public abstract class BaseUseCase<T> implements BaseInteractor<T> {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private Subscription subscription = Subscriptions.empty();

    public BaseUseCase(ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void unsubscribe() {
        if (!this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
    }

    @Override
    public void execute(RequestParams requestParams, Subscriber<T> subscriber) {
        this.subscription = getExecuteObservable(requestParams)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    @Override
    public Observable<T> getExecuteObservable(RequestParams requestParams) {
        return createObservable(requestParams);
    }

    public abstract Observable<T> createObservable(RequestParams requestParams);

}
