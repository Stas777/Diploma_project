package com.bsu.mvt.server.rest.model;

import java.io.Serializable;

public class Config implements Serializable {
    private String ftpUrl;
    private String ftpUser;
    private String ftpPassword;
    private String ftpBasePath;
    /**
     * For internal usage only
     */
    private String emailLogin;
    /**
     * For internal usage only
     */
    private String emailPassword;
    /**
     * For internal usage only
     */
    private String emailSmtpPort;
    /**
     * For internal usage only
     */
    private String emailSmtpHost;
    private String emailNotifyList;
    private String emailNotifyStatus;

    public Config() {}

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getFtpBasePath() {
        return ftpBasePath;
    }

    public void setFtpBasePath(String ftpBasePath) {
        this.ftpBasePath = ftpBasePath;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailSmtpPort() {
        return emailSmtpPort;
    }

    public void setEmailSmtpPort(String emailSmtpPort) {
        this.emailSmtpPort = emailSmtpPort;
    }

    public String getEmailSmtpHost() {
        return emailSmtpHost;
    }

    public void setEmailSmtpHost(String emailSmtpHost) {
        this.emailSmtpHost = emailSmtpHost;
    }

    public String getEmailNotifyList() {
        return emailNotifyList;
    }

    public void setEmailNotifyList(String emailNotifyList) {
        this.emailNotifyList = emailNotifyList;
    }

    public String getEmailNotifyStatus() {
        return emailNotifyStatus;
    }

    public void setEmailNotifyStatus(String emailNotifyStatus) {
        this.emailNotifyStatus = emailNotifyStatus;
    }
}
