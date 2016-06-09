package com.bsu.mvt.server.core.exception;

import java.util.List;

public class ValidationErrorBean {
    private String key;
    private String message;
    private List<Object> arguments;

    public ValidationErrorBean() {}

    public ValidationErrorBean(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public ValidationErrorBean(String key, String message, List<Object> arguments) {
        this.key = key;
        this.message = message;
        this.arguments = arguments;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public static void main(String[] args) {

    }
}