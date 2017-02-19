package com.icow.main.login.domain.interactor;

import com.icow.main.base.domain.BaseUseCase;
import com.icow.main.base.domain.RequestParams;
import com.icow.main.base.domain.executor.PostExecutionThread;
import com.icow.main.base.domain.executor.ThreadExecutor;
import com.icow.main.login.domain.model.LoginInfo;
import com.icow.main.user.domain.interactor.GetTokenUseCase;
import com.icow.main.user.domain.interactor.GetUserInfoUseCase;
import com.icow.main.user.domain.model.Token;
import com.icow.main.user.domain.model.UserInfo;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by hangnadi on 2/18/17.
 */
public class LoginEmailUseCase extends BaseUseCase<LoginInfo> {

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private final GetTokenUseCase getTokenUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;

    public LoginEmailUseCase(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             GetTokenUseCase getTokenUseCase,
                             GetUserInfoUseCase getUserInfoUseCase) {
        super(threadExecutor, postExecutionThread);
        this.getTokenUseCase = getTokenUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    @Override
    public Observable<LoginInfo> createObservable(RequestParams requestParams) {
        return getTokenObservable(requestParams)
                .flatMap(new Func1<Token, Observable<LoginInfo>>() {
                    @Override
                    public Observable<LoginInfo> call(Token token) {
                        if (token.isValid() && token.isSuccess()) {
                            RequestParams requestParams = RequestParams.create();
                            return Observable.zip(Observable.just(token),
                                    getUserInfoObservable(requestParams),
                                    getLoginInfoMapper());
                        } else {
                            LoginInfo loginInfo = new LoginInfo();
                            loginInfo.setValid(false);
                            loginInfo.setMessageError(token.getMessageError());
                            return Observable.just(loginInfo);
                        }
                    }
                });
    }

    private Func2<Token, UserInfo, LoginInfo> getLoginInfoMapper() {
        return new Func2<Token, UserInfo, LoginInfo>() {
            @Override
            public LoginInfo call(Token token, UserInfo userInfo) {
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setValid(userInfo.isValid());
                loginInfo.setSuccess(userInfo.isSuccess());
                if (userInfo.isValid() && userInfo.isSuccess()) {
                    loginInfo.setToken(token);
                    loginInfo.setUserInfo(userInfo);
                }  else {
                    loginInfo.setMessageError(userInfo.getMessageError());
                }
                return loginInfo;
            }
        };
    }

    private Observable<Token> getTokenObservable(RequestParams requestParams) {
        return getTokenUseCase.createObservable(requestParams);
    }

    private Observable<UserInfo> getUserInfoObservable(RequestParams requestParams) {
        return getUserInfoUseCase.createObservable(requestParams);
    }

}