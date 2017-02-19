package com.icow.main.base.presentation;

/**
 * Created by hangnadi on 2/10/17.
 */
public abstract class BaseDaggerPresenter<T extends BaseView> implements BasePresenter<T>{

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
        this.view.initButterKnife();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    public T getView() {
        return view;
    }

    private static class ViewNotAttachedException extends RuntimeException {
        ViewNotAttachedException() {
            super("Please call Presenter.attachView(CustomerView) before " +
                    "requesting data to the presenter");

        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

}
