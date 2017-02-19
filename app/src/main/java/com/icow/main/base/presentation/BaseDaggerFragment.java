package com.icow.main.base.presentation;

import android.os.Bundle;

import com.icow.main.di.component.HasComponent;

/**
 * Created by hangnadi on 2/12/17.
 */

public abstract class BaseDaggerFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected abstract void initInjector();

}
