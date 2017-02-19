package com.icow.main.user.data.repository;

import com.icow.main.base.domain.BaseMapParam;
import com.icow.main.user.domain.model.Token;
import com.icow.main.user.domain.model.UserInfo;

import rx.Observable;

/**
 * Created by hangnadi on 2/10/17.
 */
public interface UserRepository {

    Observable<UserInfo> getDetailUser(BaseMapParam<String, Object> queryMap);

    Observable<Token> getTokenUser(BaseMapParam<String, Object> queryMap);

}