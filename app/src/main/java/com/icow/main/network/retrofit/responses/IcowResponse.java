package com.icow.main.network.retrofit.responses;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangnadi on 2/9/17.
 */

public class IcowResponse {

    private boolean isNullData;
    private boolean isError;
    private String status;
    private String strResponse;
    private String stringData = "";
    private JSONObject jsonData;
    private List<String> errorMessages = new ArrayList<>();
    private List<String> statusMessages = new ArrayList<>();

    private Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setPrettyPrinting().create();
    private Object objData;

    public static IcowResponse factory(String stringResponse) {
        List<String> msgError = new ArrayList<>();
        List<String> msgStatus = new ArrayList<>();
        boolean isNullData = false;
        boolean isError = false;

        String status;
        JSONObject jsonResponse;
        JSONObject jsonData;

        try {
            jsonResponse = new JSONObject(stringResponse);
            status = jsonResponse.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        try {
            if (!jsonResponse.isNull("message_error")) {
                JSONArray jArray = jsonResponse.getJSONArray("message_error");
                if (jArray != null) {
                    for (int i = 0; i < jArray.length(); i++) {
                        msgError.add(jArray.get(i).toString());
                    }
                    isError = true;
                }
            } else {
                msgError.add("");
                isError = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (!jsonResponse.isNull("data")) {
                jsonData = jsonResponse.getJSONObject("data");
            } else {
                jsonData = null;
            }

            isNullData = jsonData == null;
        } catch (JSONException e) {
            e.printStackTrace();
            jsonData = null;
        }

        if (jsonData == null) {
            isError = true;
            if (msgError.isEmpty()) msgError.add("Data not found");
        }

        try {
            if (!jsonResponse.isNull("message_status")) {
                JSONArray jArray = jsonResponse.getJSONArray("message_status");
                if (jArray != null) {
                    for (int i = 0; i < jArray.length(); i++) {
                        msgStatus.add(jArray.get(i).toString());
                    }
                }
            } else {
                msgStatus.add("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        IcowResponse modelResponse = new IcowResponse();
        modelResponse.setErrorMessages(msgError);
        modelResponse.setIsError(isError);
        modelResponse.setStatus(status);
        modelResponse.setIsNullData(isNullData);
        modelResponse.setStrResponse(stringResponse);
        modelResponse.setStatusMessages(msgStatus);
        return modelResponse;
    }

    public boolean isNullData() {
        return isNullData;
    }

    private void setIsNullData(boolean isNullData) {
        this.isNullData = isNullData;
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public String getStringData() {
        return stringData;
    }

    private void setStringData(String stringData) {
        if (!stringData.isEmpty()) {
            gson.toJson(stringData);
        }
        this.stringData = stringData;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    public String getStrResponse() {
        return strResponse;
    }

    private void setStrResponse(String strResponse) {
        this.strResponse = strResponse;
    }

    private void setJsonData(@NonNull JSONObject jsonData) {
        this.stringData = jsonData.toString();
        this.jsonData = jsonData;
    }

    public boolean isError() {
        return isError;
    }

    private void setIsError(boolean isError) {
        this.isError = isError;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    private void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getStatusMessages() {
        return statusMessages;
    }

    private void setStatusMessages(List<String> statusMessages) {
        this.statusMessages = statusMessages;
    }
}
