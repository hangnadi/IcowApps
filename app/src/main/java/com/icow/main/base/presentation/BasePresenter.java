package com.icow.main.base.presentation;

/**
 * Created by hangnadi on 2/10/17.
 */

@SuppressWarnings("WeakerAccess")
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
