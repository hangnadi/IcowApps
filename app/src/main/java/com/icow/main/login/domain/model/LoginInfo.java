package com.icow.main.login.domain.model;

import com.icow.main.base.domain.BaseDomainData;
import com.icow.main.user.domain.model.Token;
import com.icow.main.user.domain.model.UserInfo;

/**
 * Created by hangnadi on 2/18/17.
 */
public class LoginInfo extends BaseDomainData {
    private UserInfo userInfo;
    private Token token;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
