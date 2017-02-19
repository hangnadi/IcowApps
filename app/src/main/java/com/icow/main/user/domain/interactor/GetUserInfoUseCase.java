package com.icow.main.user.domain.interactor;

import com.icow.main.base.domain.BaseUseCase;
import com.icow.main.base.domain.RequestParams;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.user.data.repository.UserRepository;
import com.icow.main.user.domain.model.UserInfo;

import rx.Observable;

/**
 * Created by hangnadi on 2/17/17.
 */
public class GetUserInfoUseCase extends BaseUseCase<UserInfo> {

    private final UserRepository userRepository;

    public GetUserInfoUseCase(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<UserInfo> createObservable(RequestParams requestParams) {
        return userRepository.getDetailUser(requestParams.getParameters());
    }

}
