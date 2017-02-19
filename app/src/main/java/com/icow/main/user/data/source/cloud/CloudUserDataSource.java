package com.icow.main.user.data.source.cloud;

import android.content.Context;

import com.icow.main.base.domain.BaseMapParam;
import com.icow.main.network.services.user.UserService;
import com.icow.main.user.data.mapper.TokenMapper;
import com.icow.main.user.data.mapper.UserMapper;
import com.icow.main.user.data.source.local.dbmanager.UserDbManager;
import com.icow.main.user.domain.model.Token;
import com.icow.main.user.domain.model.UserInfo;


import rx.Observable;

/**
 * Created by hangnadi on 2/16/17.
 */
public class CloudUserDataSource {

    private Context context;
    private TokenMapper tokenMapper;
    private UserService userService;
    private UserMapper userMapper;
    private UserDbManager userDbManager;

    public CloudUserDataSource(Context context,
                               UserService userService,
                               UserMapper userMapper,
                               UserDbManager userDbManager) {
        super();
        this.context = context;
        this.userService = userService;
        this.userMapper = userMapper;
        this.userDbManager = userDbManager;
    }

    public CloudUserDataSource(Context context,
                               UserService userService,
                               TokenMapper tokenMapper,
                               UserDbManager userDbManager) {
        super();
        this.context = context;
        this.userService = userService;
        this.tokenMapper = tokenMapper;
        this.userDbManager = userDbManager;
    }

    public Observable<Token> getToken(BaseMapParam<String, Object> queryMap) {
        return userService.getToken(queryMap).map(tokenMapper);
    }

    public Observable<UserInfo> getDetailUser(BaseMapParam<String, Object> queryMap) {
        return userService.getUserInfo(queryMap).map(userMapper);
    }
}
