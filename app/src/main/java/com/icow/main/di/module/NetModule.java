package com.icow.main.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icow.main.di.qualifier.ApplicationContext;
import com.icow.main.di.qualifier.BaseDomainQualifier;
import com.icow.main.di.qualifier.WithAuthInterceptorQualifier;
import com.icow.main.di.scope.ApplicationScope;
import com.icow.main.network.constants.BaseUrl;
import com.icow.main.network.retrofit.converters.IcowResponseConverter;
import com.icow.main.network.retrofit.converters.StringResponseConverter;
import com.icow.main.network.retrofit.interceptors.IcowInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hangnadi on 2/8/17.
 */
@Module
public class NetModule {


    @BaseDomainQualifier
    @ApplicationScope
    @Provides
    Retrofit provideBaseDomainRetrofit(Gson gson,
                                       @WithAuthInterceptorQualifier OkHttpClient okHttpClient,
                                       IcowResponseConverter icwResponseConverter,
                                       StringResponseConverter stringResponseConverter) {
        return new Retrofit.Builder()
                .addConverterFactory(icwResponseConverter)
                .addConverterFactory(stringResponseConverter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BaseUrl.BASE_DOMAIN)
                .client(okHttpClient)
                .build();
    }

    @WithAuthInterceptorQualifier
    @ApplicationScope
    @Provides
    OkHttpClient provideOkhttpClient(Cache cache,
                                     HttpLoggingInterceptor httpLoggingInterceptor,
                                     Interceptor authInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.connectTimeout(45, TimeUnit.SECONDS);
        client.readTimeout(45, TimeUnit.SECONDS);
        client.writeTimeout(45, TimeUnit.SECONDS);
        client.addInterceptor(authInterceptor);
        client.addInterceptor(httpLoggingInterceptor);
        client.cache(cache);
        return client.build();
    }

    @ApplicationScope
    @Provides
    Gson provideGsonFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        return gsonBuilder.create();
    }

    @ApplicationScope
    @Provides
    Cache provideHttpCache(@ApplicationContext Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @ApplicationScope
    @Provides
    Interceptor provideAuthInterceptor() {
        return new IcowInterceptor();
    }

    @ApplicationScope
    @Provides
    StringResponseConverter provideStringConverter() {
        return new StringResponseConverter();
    }

    @ApplicationScope
    @Provides
    IcowResponseConverter provideIcowConverter() {
        return new IcowResponseConverter();
    }

    @ApplicationScope
    @Provides
    HttpLoggingInterceptor provideHttpLogingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
