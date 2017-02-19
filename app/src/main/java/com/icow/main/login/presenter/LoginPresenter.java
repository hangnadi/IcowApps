package com.icow.main.login.presenter;

import com.icow.main.base.domain.RequestParams;
import com.icow.main.base.presentation.BaseDaggerPresenter;
import com.icow.main.login.domain.interactor.LoginEmailUseCase;
import com.icow.main.login.domain.interactor.LoginFacebookUseCase;
import com.icow.main.login.domain.interactor.LoginGoogleUseCase;
import com.icow.main.login.domain.model.LoginInfo;
import com.icow.main.login.view.LoginContract;

import javax.inject.Inject;

/**
 * Created by hangnadi on 2/9/17.
 */

public class LoginPresenter extends BaseDaggerPresenter<LoginContract.View>
        implements  LoginContract.Presenter {

    private final LoginEmailUseCase loginEmailUseCase;
    private final LoginFacebookUseCase loginFacebookUseCase;
    private final LoginGoogleUseCase loginGoogleUseCase;

    @Inject
    public LoginPresenter(LoginEmailUseCase loginEmailUseCase,
                          LoginFacebookUseCase loginFacebookUseCase,
                          LoginGoogleUseCase loginGoogleUseCase) {
        this.loginEmailUseCase = loginEmailUseCase;
        this.loginFacebookUseCase = loginFacebookUseCase;
        this.loginGoogleUseCase = loginGoogleUseCase;
    }

    @Override
    public void detachView() {
        super.detachView();
        loginEmailUseCase.unsubscribe();
        loginFacebookUseCase.unsubscribe();
        loginGoogleUseCase.unsubscribe();
    }

    @Override
    public void onSubmitButtonClick() {
        loginEmailUseCase.execute(getLoginEmailRequestParams(), new LoginEmailSubscriber());
    }

    private RequestParams getLoginEmailRequestParams() {
        RequestParams requestParams = RequestParams.create();
        requestParams.putString(LoginEmailUseCase.EMAIL, getEmailUserInput());
        requestParams.putString(LoginEmailUseCase.PASSWORD, getPasswordUserInput());
        return requestParams;
    }

    private String getEmailUserInput() {
        return getView().getEmail();
    }

    private String getPasswordUserInput() {
        return getView().getPassword();
    }

    private class LoginEmailSubscriber extends rx.Subscriber<LoginInfo> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(LoginInfo loginInfo) {

        }
    }
}
