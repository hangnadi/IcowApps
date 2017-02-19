package com.icow.main.base.domain;

/**
 * Created by hangnadi on 2/19/17.
 */

public class BaseDomainData {

    private boolean valid;
    private boolean success;
    private String messageError;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
