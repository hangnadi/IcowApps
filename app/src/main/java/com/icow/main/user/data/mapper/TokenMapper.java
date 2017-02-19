package com.icow.main.user.data.mapper;

import com.google.gson.Gson;
import com.icow.main.base.domain.BaseMapper;
import com.icow.main.network.entity.user.TokenEntity;
import com.icow.main.network.retrofit.responses.IcowResponse;
import com.icow.main.user.domain.model.Token;

import retrofit2.Response;

/**
 * Created by hangnadi on 2/17/17.
 */
public class TokenMapper extends BaseMapper<Response<IcowResponse>, Token> {

    public TokenMapper(Gson gson) {
        super(gson);
    }

    @Override
    public Token call(Response<IcowResponse> response) {
        return mappingResponse(response);
    }

    private Token mappingResponse(Response<IcowResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            if (!response.body().isError()) {
                TokenEntity tokenEntity = generateGson(response, TokenEntity.class);
                if (tokenEntity != null) {
                    return mappingValidTokenResponse(tokenEntity);
                } else {
                    return mappingInvalidTokenResponse();
                }
            } else {
                return mappingErrorTokenResponse(generateErrorMessage(response));
            }
        } else {
            return mappingInvalidTokenResponse();
        }
    }

    private Token mappingValidTokenResponse(TokenEntity tokenEntity) {
        Token token = new Token();
        token.setValid(true);
        token.setSuccess(true);
        token.setAccessToken(tokenEntity.getAccessToken());
        token.setTokenType(tokenEntity.getTokenType());
        return token;
    }

    private Token mappingInvalidTokenResponse() {
        Token token = new Token();
        token.setValid(false);
        return token;
    }

    private Token mappingErrorTokenResponse(String errorMessage) {
        Token token = new Token();
        token.setValid(true);
        token.setSuccess(false);
        token.setMessageError(errorMessage);
        return null;
    }
}
