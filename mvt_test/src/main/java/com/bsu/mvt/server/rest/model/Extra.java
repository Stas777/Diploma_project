package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Extra implements Serializable {
    private Long id;
    private Sample sample;
    private Activity activity;
    private String type;
    private String key;
    private String value;

    public Extra() {}

    public Extra(Long id) {
        this.id = id;
    }

    public Extra(Sample sample, String type, String key, String value) {
        this.sample = sample;
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public Extra(Activity activity, String type, String key, String value) {
        this.activity = activity;
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Extra extra = (Extra) o;

        if (activity != null ? !activity.equals(extra.activity) : extra.activity != null) return false;
        if (id != null ? !id.equals(extra.id) : extra.id != null) return false;
        if (key != null ? !key.equals(extra.key) : extra.key != null) return false;
        if (sample != null ? !sample.equals(extra.sample) : extra.sample != null) return false;
        if (type != null ? !type.equals(extra.type) : extra.type != null) return false;
        if (value != null ? !value.equals(extra.value) : extra.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Extra{");
        sb.append("id=").append(id);
        sb.append(", sample=").append(sample);
        sb.append(", activity=").append(activity);
        sb.append(", type='").append(type).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
