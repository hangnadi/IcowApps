package com.icow.main.login.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.icow.main.R;
import com.icow.main.base.presentation.BaseActivity;
import com.icow.main.login.di.component.DaggerLoginComponent;
import com.icow.main.login.di.component.LoginComponent;
import com.icow.main.login.di.module.LoginModule;
import com.icow.main.login.presenter.LoginPresenter;
import com.icow.main.user.di.component.DaggerUserComponent;
import com.icow.main.user.di.component.UserComponent;
import com.icow.main.user.di.module.UserModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity
        implements LoginContract.View {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.emailEditText)
    EditText emailEditText;

    @BindView(R.id.passwordEditText)
    EditText passwordEditText;

    @OnClick(R.id.submitButton)
    public void onSubmitButtonClick() {
        if (isLoginValid()) {
            presenter.onSubmitButtonClick();
        }
    }

    @OnClick(R.id.loginFacebookButton)
    public void onFacebookButtonClick() {

    }

    @OnClick(R.id.loginGoogleButton)
    public void onLoginButtonClick() {

    }

    @Override
    public boolean isLoginValid() {
        boolean valid = true;

        if (getEmail().isEmpty()) {
            emailEditText.setError(getString(R.string.error_field_required));
            emailEditText.requestFocus();
            valid = false;
        }

        if (getPassword().isEmpty()) {
            passwordEditText.setError(getString(R.string.error_field_required));
            emailEditText.requestFocus();
            valid = false;
        }

        return valid;
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        injectDagger();
        presenter.attachView(this);
    }

    private void injectDagger() {
        UserComponent userComponent = DaggerUserComponent.builder()
                .appComponent(getAppComponent())
                .userModule(new UserModule())
                .build();

        LoginComponent loginComponent = DaggerLoginComponent.builder()
                .userComponent(userComponent)
                .loginModule(new LoginModule())
                .build();

        loginComponent.inject(this);
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
