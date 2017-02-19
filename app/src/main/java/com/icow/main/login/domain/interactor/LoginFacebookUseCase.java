package com.icow.main.login.domain.interactor;

import com.icow.main.base.domain.BaseUseCase;
import com.icow.main.base.domain.RequestParams;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.login.domain.model.LoginInfo;
import com.icow.main.user.data.repository.UserRepository;

import rx.Observable;

/**
 * Created by hangnadi on 2/10/17.
 */
public class LoginFacebookUseCase extends BaseUseCase<LoginInfo> {

    private final UserRepository userRepository;

    public LoginFacebookUseCase(ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread,
                                UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<LoginInfo> createObservable(RequestParams requestParams) {
        return null;
    }

}
