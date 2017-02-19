package com.icow.main.user.domain.interactor;

import com.icow.main.base.domain.BaseUseCase;
import com.icow.main.base.domain.RequestParams;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.user.data.repository.UserRepository;
import com.icow.main.user.domain.model.Token;

import rx.Observable;

/**
 * Created by hangnadi on 2/17/17.
 */

public class GetTokenUseCase extends BaseUseCase<Token> {

    private final UserRepository userRepository;

    public GetTokenUseCase(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable<Token> createObservable(RequestParams requestParams) {
        return userRepository.getTokenUser(requestParams.getParameters());
    }

}
