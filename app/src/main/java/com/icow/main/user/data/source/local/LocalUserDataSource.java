package com.icow.main.user.data.source.local;

import com.icow.main.user.data.mapper.UserMapper;
import com.icow.main.user.data.source.local.dbmanager.UserDbManager;
import com.icow.main.user.domain.model.UserInfo;

import rx.Observable;

/**
 * Created by hangnadi on 2/14/17.
 */
public class LocalUserDataSource {

    private final UserDbManager userDbManager;
    private final UserMapper userMapper;

    public LocalUserDataSource(UserDbManager userDbManager, UserMapper userMapper) {
        this.userDbManager = userDbManager;
        this.userMapper = userMapper;
    }

    public Observable<UserInfo> getUserCache() {
        if (userDbManager.isExpired(System.currentTimeMillis())) {
            return Observable.empty();
        }
        return userDbManager.getData().map(userMapper);
    }
}
