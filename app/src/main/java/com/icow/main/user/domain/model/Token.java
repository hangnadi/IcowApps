package com.icow.main.user.domain.model;

import com.icow.main.base.domain.BaseDomainData;

/**
 * Created by hangnadi on 2/18/17.
 */
public class Token extends BaseDomainData {
    private String accessToken;
    private String tokenType;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

}
