package com.icow.main.login.view;

import com.icow.main.base.presentation.BaseView;

/**
 * Created by hangnadi on 2/10/17.
 */

public interface LoginContract {
    interface View extends BaseView {
        boolean isLoginValid();

        String getEmail();

        String getPassword();
    }

    interface Presenter {
        void onSubmitButtonClick();
    }
}
