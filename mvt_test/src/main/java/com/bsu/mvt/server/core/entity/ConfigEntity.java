package com.bsu.mvt.server.core.entity;

public class ConfigEntity {
    public static final String FTP_BASEPATH = "ftp.basepath";
    public static final String FTP_PASSWORD = "ftp.password";
    public static final String FTP_URL = "ftp.url";
    public static final String FTP_USER = "ftp.user";
    public static final String EMAIL_LOGIN = "email.login";
    public static final String EMAIL_PASSWORD = "email.password";
    public static final String EMAIL_SMTP_PORT = "email.smtp.port";
    public static final String EMAIL_SMTP_HOST = "email.smtp.host";
    public static final String EMAIL_NOTIFY_LIST = "email.notify_list";
    public static final String EMAIL_NOTIFY_STATUS = "email.notify_status";

    private String name;
    private String value;

    public ConfigEntity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
