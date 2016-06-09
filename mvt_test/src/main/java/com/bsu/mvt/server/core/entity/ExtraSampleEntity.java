package com.bsu.mvt.server.core.entity;

public class ExtraSampleEntity {
    private Long id;
    private Long sample_id;
    private String type;
    private String key;
    private String value;

    public ExtraSampleEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSample_id() {
        return sample_id;
    }

    public void setSample_id(Long sample_id) {
        this.sample_id = sample_id;
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
}
