package com.icow.main.user.data.mapper;

import com.google.gson.Gson;
import com.icow.main.user.domain.model.UserInfo;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * Created by hangnadi on 2/13/17.
 */
public class UserMapper implements Func1<Response<String>, UserInfo> {

    private final Gson gson;

    public UserMapper(Gson gson) {
        this.gson = gson;
    }

    @Override
    public UserInfo call(Response<String> response) {
        return mappingResponse(response);
    }

    private UserInfo mappingResponse(Response<String> response) {
        if (response.isSuccessful() && response.body() != null) {
//            ProductFeedData3 feedResponse = gson.fromJson(response.body(), ProductFeedData3.class);
//            if (feedResponse != null) {
//                return mappingValidFeedResponse(feedResponse);
//            } else {
//                return mappingInvalidFeedResponse();
//            }
            return null;
        } else {
            return mappingInvalidFeedResponse();
        }
    }

    private UserInfo mappingInvalidFeedResponse() {
        UserInfo userInfo = new UserInfo();
        userInfo.setValid(false);
        return userInfo;
    }
}
