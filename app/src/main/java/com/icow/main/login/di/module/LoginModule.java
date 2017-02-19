package com.icow.main.login.di.module;

import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.login.di.scope.LoginScope;
import com.icow.main.login.domain.interactor.LoginEmailUseCase;
import com.icow.main.login.domain.interactor.LoginFacebookUseCase;
import com.icow.main.login.domain.interactor.LoginGoogleUseCase;
import com.icow.main.login.presenter.LoginPresenter;
import com.icow.main.network.services.user.UserService;
import com.icow.main.user.data.repository.UserRepository;
import com.icow.main.user.domain.interactor.GetTokenUseCase;
import com.icow.main.user.domain.interactor.GetUserInfoUseCase;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hangnadi on 2/18/17.
 */
@Module
public class LoginModule {

    @LoginScope
    @Provides
    UserService provideLoginService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @LoginScope
    @Provides
    LoginPresenter provideLoginPresenter(LoginEmailUseCase loginEmailUseCase,
                                         LoginFacebookUseCase loginFacebookUseCase,
                                         LoginGoogleUseCase loginGoogleUseCase) {
        return new LoginPresenter(
                loginEmailUseCase,
                loginFacebookUseCase,
                loginGoogleUseCase
        );
    }

    @LoginScope
    @Provides
    LoginEmailUseCase provideLoginEmailUseCase(ThreadExecutor threadExecutor,
                                               PostExecutionThread postExecutionThread,
                                               UserRepository userRepository,
                                               GetTokenUseCase getTokenUseCase,
                                               GetUserInfoUseCase getUserInfoUseCase) {
        return new LoginEmailUseCase(threadExecutor,
                postExecutionThread,
                getTokenUseCase,
                getUserInfoUseCase
        );
    }

    @LoginScope
    @Provides
    LoginFacebookUseCase provideLoginFacebooklUseCase(ThreadExecutor threadExecutor,
                                                      PostExecutionThread postExecutionThread,
                                                      UserRepository userRepository) {
        return new LoginFacebookUseCase(threadExecutor, postExecutionThread, userRepository);
    }

    @LoginScope
    @Provides
    LoginGoogleUseCase provideLoginGooglelUseCase(ThreadExecutor threadExecutor,
                                                  PostExecutionThread postExecutionThread,
                                                  UserRepository userRepository) {
        return new LoginGoogleUseCase(threadExecutor, postExecutionThread, userRepository);
    }
}
