package com.bsu.mvt.server.core.exception;

public class MVTErrorMessage {
    private String message;
    private String code;
    private String causeMessage;

    public MVTErrorMessage(MVTException ex) {
        message = ex.getMvtMessage();
        code = ex.getMvtCode();
        causeMessage = ex.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCauseMessage() {
        return causeMessage;
    }

    public void setCauseMessage(String causeMessage) {
        this.causeMessage = causeMessage;
    }
}
