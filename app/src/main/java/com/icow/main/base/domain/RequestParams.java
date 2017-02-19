package com.icow.main.base.domain;

/**
 * Created by hangnadi on 2/10/17.
 */
@SuppressWarnings("unused")
public class RequestParams {
    public static final RequestParams EMPTY = RequestParams.create();

    private final BaseMapParam<String, Object> parameters = new BaseMapParam<>();

    private RequestParams() {
    }

    public static RequestParams create() {
        return new RequestParams();
    }

    public void putInt(String key, int value) {
        parameters.put(key, value);
    }

    public void putString(String key, String value) {
        parameters.put(key, value);
    }

    public void putBoolean(String key, boolean value) {
        parameters.put(key, value);
    }

    public int getInt(String key, int defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (int) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (String) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (Boolean) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public void clearValue(String key) {
        parameters.remove(key);
    }

    public BaseMapParam<String, Object> getParameters() {
        return parameters;
    }
}
