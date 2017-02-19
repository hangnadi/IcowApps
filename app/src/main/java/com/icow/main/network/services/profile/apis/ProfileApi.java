package com.icow.main.network.services.profile.apis;

import com.icow.main.network.constants.BaseUrl;
import com.icow.main.network.retrofit.responses.IcowResponse;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Example only
 * Created by hangnadi on 2/8/17.
 */

public interface ProfileApi {

    @GET(BaseUrl.Profile.PATH_GET_PROFILE_INFO)
    Observable<Response<IcowResponse>> getProfileInfo(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST(BaseUrl.Profile.PATH_SUBMIT_PROFILE)
    Observable<Response<IcowResponse>> submitProfile(@FieldMap Map<String, String> params);

}
