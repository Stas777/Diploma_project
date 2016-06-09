package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Log implements Serializable {
    private Long id;
    private User user;
    private Sample sample;
    private Action action;

    public Log() {}

    public Log(User user, Sample sample, Action action) {
        this.user = user;
        this.sample = sample;
        this.action = action;
    }

    public Log(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (action != null ? !action.equals(log.action) : log.action != null) return false;
        if (id != null ? !id.equals(log.id) : log.id != null) return false;
        if (sample != null ? !sample.equals(log.sample) : log.sample != null) return false;
        if (user != null ? !user.equals(log.user) : log.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Log{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", sample=").append(sample);
        sb.append(", action=").append(action);
        sb.append('}');
        return sb.toString();
    }
}
