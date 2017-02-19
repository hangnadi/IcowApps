package com.icow.main.user.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.di.qualifier.ActivityContext;
import com.icow.main.di.qualifier.BaseDomainQualifier;
import com.icow.main.network.services.user.UserService;
import com.icow.main.user.data.factory.UserDataSourceFactory;
import com.icow.main.user.data.mapper.TokenMapper;
import com.icow.main.user.data.mapper.UserMapper;
import com.icow.main.user.data.repository.UserRepository;
import com.icow.main.user.data.repository.UserRepositoryImpl;
import com.icow.main.user.data.source.local.dbmanager.UserDbManager;
import com.icow.main.user.di.scope.UserScope;
import com.icow.main.user.domain.interactor.GetTokenUseCase;
import com.icow.main.user.domain.interactor.GetUserInfoUseCase;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hangnadi on 2/18/17.
 */
@Module
public class UserModule {

    @UserScope
    @Provides
    UserRepository provideUserRepository(UserDataSourceFactory userDataSourceFactory) {
        return new UserRepositoryImpl(userDataSourceFactory);
    }

    @UserScope
    @Provides
    UserDataSourceFactory provideUserDataSourceFactory(@ActivityContext Context context,
                                                       UserService userService,
                                                       UserMapper userMapper,
                                                       TokenMapper tokenMapper,
                                                       UserDbManager userDbManager) {
        return new UserDataSourceFactory(context, userService, userMapper, tokenMapper, userDbManager);
    }

    @UserScope
    @Provides
    UserService provideUserService(@BaseDomainQualifier Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @UserScope
    @Provides
    UserMapper provideUserMapper(Gson gson) {
        return new UserMapper(gson);
    }

    @UserScope
    @Provides
    TokenMapper provideTokenMapper(Gson gson) {
        return new TokenMapper(gson);
    }

    @UserScope
    @Provides
    UserDbManager provideUserDbManager() {
        return new UserDbManager();
    }

    @UserScope
    @Provides
    GetTokenUseCase provideGetTokenUseCase(ThreadExecutor threadExecutor,
                                           PostExecutionThread postExecutionThread,
                                           UserRepository userRepository) {
        return new GetTokenUseCase(threadExecutor, postExecutionThread, userRepository);
    }

    @UserScope
    @Provides
    GetUserInfoUseCase provideGetUserInfoUseCase(ThreadExecutor threadExecutor,
                                                 PostExecutionThread postExecutionThread,
                                                 UserRepository userRepository) {
        return new GetUserInfoUseCase(threadExecutor, postExecutionThread, userRepository);
    }

}
