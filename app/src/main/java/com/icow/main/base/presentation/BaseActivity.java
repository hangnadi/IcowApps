package com.icow.main.base.presentation;

import android.support.v7.app.AppCompatActivity;

import com.icow.main.MainApplication;
import com.icow.main.di.component.AppComponent;
import com.icow.main.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    protected AppComponent getAppComponent() {
        return ((MainApplication) getApplication()).getApplicationComponent(getActivityModule());
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
