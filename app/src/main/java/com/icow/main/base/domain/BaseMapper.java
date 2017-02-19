package com.icow.main.base.domain;

import com.google.gson.Gson;
import com.icow.main.network.retrofit.responses.IcowResponse;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * Created by hangnadi on 2/19/17.
 */

public abstract class BaseMapper<T, R> implements Func1<T, R> {

    private final Gson gson;

    public BaseMapper(Gson gson) {
        this.gson = gson;
    }

    protected <P> P generateGson(Response<IcowResponse> subject, Class<P> object) {
        return gson.fromJson(subject.body().getStringData(), object);
    }

    protected String generateErrorMessage(Response<IcowResponse> response) {
        String compileMessage = "";
        for (int i = 0; i < response.body().getErrorMessages().size(); i++) {
            if (i == response.body().getErrorMessages().size() - 1) {
                compileMessage = compileMessage + response.body().getErrorMessages().get(i);
            } else {
                compileMessage = compileMessage + response.body().getErrorMessages().get(i) + "\n";
            }
        }
        return compileMessage;
    }
}
