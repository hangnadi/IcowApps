package com.icow.main.login.di.component;

import com.icow.main.login.di.module.LoginModule;
import com.icow.main.login.di.scope.LoginScope;
import com.icow.main.login.view.LoginActivity;
import com.icow.main.user.di.component.UserComponent;

import dagger.Component;

/**
 * Created by hangnadi on 2/18/17.
 */
@LoginScope
@Component(modules = LoginModule.class, dependencies = UserComponent.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
