package com.my.accountmanager.model;

public class TrxValidatorMessages {
    private String message;
    private boolean failValidation;

    public TrxValidatorMessages() {
    }

    public TrxValidatorMessages(String message, boolean failValidation) {
        this.message = message;
        this.failValidation = failValidation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getFailValidation() {
        return failValidation;
    }

    public void setFailValidation(boolean failValidation) {
        this.failValidation = failValidation;
    }
}
