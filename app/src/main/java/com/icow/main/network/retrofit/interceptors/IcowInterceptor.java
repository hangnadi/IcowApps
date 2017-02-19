package com.icow.main.network.retrofit.interceptors;

import android.util.Log;

import com.icow.main.network.retrofit.utils.AuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by hangnadi on 2/9/17.
 */

public class IcowInterceptor implements Interceptor {

    private static final String TAG = IcowInterceptor.class.getSimpleName();
    private static final int STATUS_SERVER_ERROR = 1;
    private static final int STATUS_REQUEST_DENIED = 2;
    private static final int STATUS_MAINTENANCE = 3;

    private final String authKey;

    public IcowInterceptor() {
        this.authKey = AuthUtil.KEY.KEY_WSV4;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originRequest = chain.request();
        Request.Builder newRequest = chain.request().newBuilder();

        generateHmacAuthRequest(originRequest, newRequest);

        final Request finalRequest = newRequest.build();
        Response response = chain.proceed(finalRequest);
        int count = 0;
        while (!response.isSuccessful() && count < 3) {
            Log.d(TAG, "NOT SUCCESSFUL");
            Log.d(TAG, "intercept: counter = " + count);
            Log.d(TAG, "intercept: error code = " + response.code());
            count++;
            response = chain.proceed(finalRequest);
        }

        switch (generateResponseStatus(response)) {
            case STATUS_MAINTENANCE:
                showMaintenance();
                break;
            case STATUS_REQUEST_DENIED:
                forceLogout();
                break;
            case STATUS_SERVER_ERROR:
                showServerError();
                break;
            default:
                break;
        }

        return createNewResponse(response);
    }

    private void generateHmacAuthRequest(Request originRequest, Request.Builder newRequest) {
        Map<String, String> authHeaders = new HashMap<>();
        authHeaders = prepareHeader(authHeaders, originRequest);
        generateHeader(authHeaders, originRequest, newRequest);
    }

    private Map<String, String> prepareHeader(Map<String, String> authHeaders, Request originRequest) {
        switch (originRequest.method()) {
            case "PATCH":
            case "POST":
                authHeaders = getHeaderMap(originRequest.url().uri().getPath(),
                        generateParamBodyString(originRequest), originRequest.method(), authKey);
                break;
            case "GET":
                authHeaders = getHeaderMap(originRequest.url().uri().getPath(),
                        generateQueryString(originRequest), originRequest.method(), authKey);
                break;
        }
        return authHeaders;
    }

    private String generateParamBodyString(final Request request) {
        try {
            final Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "";
        }
    }

    private String generateQueryString(final Request request) {
        String query = request.url().query();
        return query != null ? query : "";
    }

    private Map<String, String> getHeaderMap(String path, String strParam, String method, String authKey) {
        return AuthUtil.generateHeaders(path, strParam, method, authKey);
    }

    private void generateHeader(Map<String, String> authHeaders, Request originRequest, Request.Builder newRequest) {
        for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
            newRequest.addHeader(entry.getKey(), entry.getValue());
        }
        newRequest.method(originRequest.method(), originRequest.body());
    }

    private void showMaintenance() {

    }

    private void forceLogout() {

    }

    private void showServerError() {

    }

    private Response createNewResponse(Response oldResponse) {
        ResponseBody body = ResponseBody
                .create(oldResponse.body().contentType(), oldResponse.body().toString());

        Response.Builder builder = new Response.Builder();
        builder.body(body)
                .headers(oldResponse.headers())
                .message(oldResponse.message())
                .handshake(oldResponse.handshake())
                .protocol(oldResponse.protocol())
                .cacheResponse(oldResponse.cacheResponse())
                .priorResponse(oldResponse.priorResponse())
                .code(oldResponse.code())
                .request(oldResponse.request())
                .networkResponse(oldResponse.networkResponse());

        return builder.build();
    }

    private int generateResponseStatus(Response response) {
        if (isMaintenance(response.body().toString())) {
            return STATUS_MAINTENANCE;
        } else if (isRequestDenied(response.body().toString())) {
            return STATUS_REQUEST_DENIED;
        } else if (isServerError(response.code())) {
            return STATUS_SERVER_ERROR;
        }
        return 0;
    }

    private Boolean isMaintenance(String response) {
        JSONObject json;
        try {
            json = new JSONObject(response);
            String status = json.optString("status", "OK");
            return status.equals("UNDER_MAINTENANCE");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Boolean isRequestDenied(String response) {
        JSONObject json;
        try {
            json = new JSONObject(response);
            String status = json.optString("status", "OK");
            return status.equals("REQUEST_DENIED");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Boolean isServerError(int code) {
        return code >= 500;
    }

}
