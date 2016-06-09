package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Action implements Serializable {
    private Long id;
    private String name;
    private String data;
    private Date date;

    public Action() {}

    public Action(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Action(Long id, String name, String data, Date date) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.date = date;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (data != null ? !data.equals(action.data) : action.data != null) return false;
        if (date != null ? !date.equals(action.date) : action.date != null) return false;
        if (id != null ? !id.equals(action.id) : action.id != null) return false;
        if (name != null ? !name.equals(action.name) : action.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Action{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
