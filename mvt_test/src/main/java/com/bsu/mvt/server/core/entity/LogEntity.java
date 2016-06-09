package com.bsu.mvt.server.core.entity;

import java.util.Date;

public class LogEntity {
    private Long id;
    private Long user_id;
    private Long sample_id;
    private Long action_id;
    private String action_name;
    private String action_data;
    private Date action_date;

    public LogEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSample_id() {
        return sample_id;
    }

    public void setSample_id(Long sample_id) {
        this.sample_id = sample_id;
    }

    public Long getAction_id() {
        return action_id;
    }

    public void setAction_id(Long action_id) {
        this.action_id = action_id;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getAction_data() {
        return action_data;
    }

    public void setAction_data(String action_data) {
        this.action_data = action_data;
    }

    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }
}
