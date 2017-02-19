package com.icow.main.di.module;

import android.content.Context;

import com.icow.main.di.qualifier.ActivityContext;
import com.icow.main.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hangnadi on 2/8/17.
 */
@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    @ActivityContext
    Context provideContext() {
        return context;
    }
}
