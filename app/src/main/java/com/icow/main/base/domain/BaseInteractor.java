package com.icow.main.base.domain;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hangnadi on 2/10/17.
 */
@SuppressWarnings("WeakerAccess")
public interface BaseInteractor<T> {

    void execute(RequestParams requestParams, Subscriber<T> subscriber);

    Observable<T> getExecuteObservable(RequestParams requestParams);

}
