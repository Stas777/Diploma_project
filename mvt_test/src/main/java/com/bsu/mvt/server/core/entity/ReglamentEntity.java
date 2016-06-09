package com.bsu.mvt.server.core.entity;

public class ReglamentEntity {
    private Long id;
    private String name;
    private Float max_qualification_error;
    private Float max_classification_error;
    private Float min_activity_match;

    private Boolean scope_sport;
    private Boolean scope_sample;
    private Boolean scope_activity;
    private Boolean scope_motion_type;

    public ReglamentEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMax_qualification_error() {
        return max_qualification_error;
    }

    public void setMax_qualification_error(Float max_qualification_error) {
        this.max_qualification_error = max_qualification_error;
    }

    public Float getMax_classification_error() {
        return max_classification_error;
    }

    public void setMax_classification_error(Float max_classification_error) {
        this.max_classification_error = max_classification_error;
    }

    public Float getMin_activity_match() {
        return min_activity_match;
    }

    public void setMin_activity_match(Float min_activity_match) {
        this.min_activity_match = min_activity_match;
    }

    public Boolean getScope_sport() {
        return scope_sport;
    }

    public void setScope_sport(Boolean scope_sport) {
        this.scope_sport = scope_sport;
    }

    public Boolean getScope_sample() {
        return scope_sample;
    }

    public void setScope_sample(Boolean scope_sample) {
        this.scope_sample = scope_sample;
    }

    public Boolean getScope_activity() {
        return scope_activity;
    }

    public void setScope_activity(Boolean scope_activity) {
        this.scope_activity = scope_activity;
    }

    public Boolean getScope_motion_type() {
        return scope_motion_type;
    }

    public void setScope_motion_type(Boolean scope_motion_type) {
        this.scope_motion_type = scope_motion_type;
    }
}
