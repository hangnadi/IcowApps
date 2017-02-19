package com.icow.main.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.base.presentation.BaseActivity;
import com.icow.main.di.module.AppModule;
import com.icow.main.di.qualifier.ActivityContext;
import com.icow.main.di.qualifier.ApplicationContext;
import com.icow.main.di.qualifier.BaseDomainQualifier;
import com.icow.main.di.scope.ApplicationScope;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by hangnadi on 2/8/17.
 */
@ApplicationScope
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    @ApplicationContext
    Context applicationContext();

    @ActivityContext
    Context activityContext();

    @BaseDomainQualifier
    Retrofit retrofit();

    Gson gson();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

}
