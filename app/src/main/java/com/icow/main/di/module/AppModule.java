package com.icow.main.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.icow.main.base.domain.executor.JobExecutor;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.base.domain.executor.UIThread;
import com.icow.main.di.qualifier.ApplicationContext;
import com.icow.main.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hangnadi on 2/8/17.
 */
@Module(includes = {ActivityModule.class, NetModule.class})
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    @ApplicationContext
    Context provideContext() {
        return context.getApplicationContext();
    }

    @ApplicationScope
    @Provides
    SharedPreferences provideSharedPreference(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @ApplicationScope
    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @ApplicationScope
    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


}
