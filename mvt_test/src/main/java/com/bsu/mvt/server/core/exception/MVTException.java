package com.bsu.mvt.server.core.exception;

import java.util.*;

public class MVTException extends RuntimeException {
    private String mvtMessage;
    private String mvtCode;
    private List<ValidationErrorBean> validationErrorBeans;

    public MVTException() {}

    public MVTException(String message) {
        super(message);
    }

    public MVTException(String message, Throwable cause) {
        super(message, cause);
    }

    public MVTException(Throwable cause) {
        super(cause);
    }

    public MVTException(Throwable cause, String mvtMessage, String mvtCode) {
        super(cause);
        this.mvtMessage = mvtMessage;
        this.mvtCode = mvtCode;
    }

    public MVTException(Throwable cause, String mvtMessage, String mvtCode, ValidationErrorBean... validationErrorBeans) {
        super(cause);
        this.mvtMessage = mvtMessage;
        this.mvtCode = mvtCode;
        setValidationErrorBeans(validationErrorBeans);
    }

    public MVTException(String mvtMessage, String mvtCode, ValidationErrorBean... validationErrorBeans) {
        super(mvtMessage);
        this.mvtMessage = mvtMessage;
        this.mvtCode = mvtCode;
        setValidationErrorBeans(validationErrorBeans);
    }

    public MVTException(String mvtMessage, String mvtCode) {
        super(mvtMessage);
        this.mvtMessage = mvtMessage;
        this.mvtCode = mvtCode;
    }

    public MVTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getMvtMessage() {
        return mvtMessage;
    }

    public void setMvtMessage(String mvtMessage) {
        this.mvtMessage = mvtMessage;
    }

    public String getMvtCode() {
        return mvtCode;
    }

    public void setMvtCode(String mvtCode) {
        this.mvtCode = mvtCode;
    }

    public List<ValidationErrorBean> getValidationErrorBeans() {
        return validationErrorBeans;
    }

    public void setValidationErrorBeans(ValidationErrorBean... validationErrorBeans) {
        this.validationErrorBeans = Arrays.asList(validationErrorBeans);
    }
}
