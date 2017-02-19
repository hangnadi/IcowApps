package com.icow.main.network.services.user;

import com.icow.main.network.constants.BaseUrl;
import com.icow.main.network.retrofit.responses.IcowResponse;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hangnadi on 2/9/17.
 */
public interface UserService {

    @FormUrlEncoded
    @POST(BaseUrl.User.PATH_LOGIN)
    Observable<Response<IcowResponse>> getToken(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST(BaseUrl.User.PATH_REGISTER)
    Observable<Response<String>> register(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST(BaseUrl.User.PATH_RESET_PASSWORD)
    Observable<Response<String>> resetPassword(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST(BaseUrl.User.PATH_GET_USER_INFO)
    Observable<Response<String>> getUserInfo(@FieldMap Map<String, Object> params);

}
