package com.bsu.mvt.server.core.entity;

import java.util.Date;

public class SensorDataFileEntity {
    private Long id;
    private String path;
    private String file_hash;
    private String description;
    private Date create_date;
    private Long sample_id;
    private Long qualifier_key_id;

    public SensorDataFileEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile_hash() {
        return file_hash;
    }

    public void setFile_hash(String file_hash) {
        this.file_hash = file_hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getSample_id() {
        return sample_id;
    }

    public void setSample_id(Long sample_id) {
        this.sample_id = sample_id;
    }

    public Long getQualifier_key_id() {
        return qualifier_key_id;
    }

    public void setQualifier_key_id(Long qualifier_key_id) {
        this.qualifier_key_id = qualifier_key_id;
    }
}
