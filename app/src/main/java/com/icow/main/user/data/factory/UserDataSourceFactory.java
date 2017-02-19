package com.icow.main.user.data.factory;

import android.content.Context;

import com.icow.main.network.services.user.UserService;
import com.icow.main.user.data.mapper.TokenMapper;
import com.icow.main.user.data.mapper.UserMapper;
import com.icow.main.user.data.source.local.LocalUserDataSource;
import com.icow.main.user.data.source.local.dbmanager.UserDbManager;
import com.icow.main.user.data.source.cloud.CloudUserDataSource;

/**
 * Created by hangnadi on 2/13/17.
 */
public class UserDataSourceFactory {

    private Context context;
    private UserService userService;
    private UserMapper userMapper;
    private TokenMapper tokenMapper;
    private UserDbManager userDbManager;

    public UserDataSourceFactory(Context context,
                                 UserService userService,
                                 UserMapper userMapper,
                                 TokenMapper tokenMapper,
                                 UserDbManager userDbManager) {
        this.context = context;
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenMapper = tokenMapper;
        this.userDbManager = userDbManager;
    }

    public CloudUserDataSource createUserDataSource() {
        return new CloudUserDataSource(context, userService, userMapper, userDbManager);
    }

    public CloudUserDataSource createTokenDataSource() {
        return new CloudUserDataSource(context, userService, tokenMapper, userDbManager);
    }

    public LocalUserDataSource createUserCacheDataSource() {
        return new LocalUserDataSource(userDbManager, userMapper);
    }

}
