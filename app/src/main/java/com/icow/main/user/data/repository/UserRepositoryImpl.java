package com.icow.main.user.data.repository;

import com.icow.main.base.domain.BaseMapParam;
import com.icow.main.user.data.factory.UserDataSourceFactory;
import com.icow.main.user.data.source.cloud.CloudUserDataSource;
import com.icow.main.user.domain.model.Token;
import com.icow.main.user.domain.model.UserInfo;

import rx.Observable;

/**
 * Created by hangnadi on 2/13/17.
 */
public class UserRepositoryImpl implements UserRepository {

    private final UserDataSourceFactory userDataSourceFactory;

    public UserRepositoryImpl(UserDataSourceFactory userDataSourceFactory) {
        this.userDataSourceFactory = userDataSourceFactory;
    }

    @Override
    public Observable<UserInfo> getDetailUser(BaseMapParam<String, Object> queryMap) {
        return userDataSourceFactory.createUserDataSource().getDetailUser(queryMap);
    }

    @Override
    public Observable<Token> getTokenUser(BaseMapParam<String, Object> queryMap) {
        return userDataSourceFactory.createTokenDataSource().getToken(queryMap);
    }
}
