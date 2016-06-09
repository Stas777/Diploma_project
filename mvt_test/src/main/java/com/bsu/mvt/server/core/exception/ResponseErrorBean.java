package com.bsu.mvt.server.core.exception;

import java.util.List;

public class ResponseErrorBean {
    protected Integer httpCode;
    protected String code;
    protected String message;
    protected List<ValidationErrorBean> errors;

    public ResponseErrorBean() {}

    public ResponseErrorBean(Integer httpCode, String code) {
        this.httpCode = httpCode;
        this.code = code;
    }

    public ResponseErrorBean(Integer httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public ResponseErrorBean(Integer httpCode, String code, String message, List<ValidationErrorBean> errors) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorBean> errors) {
        this.errors = errors;
    }
}
