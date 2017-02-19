package com.icow.main;

import android.app.Application;

import com.icow.main.di.component.AppComponent;
import com.icow.main.di.component.DaggerAppComponent;
import com.icow.main.di.module.ActivityModule;
import com.icow.main.di.module.AppModule;
import com.icow.main.di.module.NetModule;

/**
 * Created by hangnadi on 2/18/17.
 */

public class MainApplication extends Application {

    private DaggerAppComponent.Builder mBuilder;

    @Override
    public void onCreate() {
        initDagger();
        super.onCreate();
    }

    private void initDagger() {
        mBuilder = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule());
    }

    public AppComponent getApplicationComponent(ActivityModule activityModule) {
        return mBuilder.activityModule(activityModule)
                .build();
    }

}
