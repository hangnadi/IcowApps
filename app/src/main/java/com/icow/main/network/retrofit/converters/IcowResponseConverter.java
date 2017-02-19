package com.icow.main.network.retrofit.converters;

import com.icow.main.network.retrofit.responses.IcowResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by hangnadi on 2/9/17.
 */

public class IcowResponseConverter extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (IcowResponse.class == type) {
            return new Converter<ResponseBody, IcowResponse>() {
                @Override
                public IcowResponse convert(ResponseBody value) throws IOException {
                    return IcowResponse.factory(value.string());
                }
            };
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        if (IcowResponse.class == type) {
            return new Converter<IcowResponse, RequestBody>() {
                @Override
                public RequestBody convert(IcowResponse value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value.getStrResponse());
                }
            };
        }
        return null;
    }
}
