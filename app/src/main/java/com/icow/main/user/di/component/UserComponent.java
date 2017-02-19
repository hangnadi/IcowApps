package com.icow.main.user.di.component;

import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.di.component.AppComponent;
import com.icow.main.user.data.repository.UserRepository;
import com.icow.main.user.di.module.UserModule;
import com.icow.main.user.di.scope.UserScope;
import com.icow.main.user.domain.interactor.GetTokenUseCase;
import com.icow.main.user.domain.interactor.GetUserInfoUseCase;

import dagger.Component;

/**
 * Created by hangnadi on 2/18/17.
 */
@UserScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {

    UserRepository userRepository();

    GetTokenUseCase getTokenUseCase();

    GetUserInfoUseCase getUserInfoUseCase();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

}
