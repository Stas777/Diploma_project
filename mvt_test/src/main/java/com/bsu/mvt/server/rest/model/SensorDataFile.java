package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class  SensorDataFile implements Serializable {
    private Long id;
    private String description;
    private String path;
    private String hash;
    private Date createDate;
    private Sample sample;
    private QualifierKey qualifierKey;

    public SensorDataFile() {}

    public SensorDataFile(Long id) {
        this.id = id;
    }

    public SensorDataFile(String description, String path, String hash, Date createDate, Sample sample, QualifierKey qualifierKey) {
        this.description = description;
        this.path = path;
        this.hash = hash;
        this.createDate = createDate;
        this.sample = sample;
        this.qualifierKey = qualifierKey;
    }

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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public QualifierKey getQualifierKey() {
        return qualifierKey;
    }

    public void setQualifierKey(QualifierKey qualifierKey) {
        this.qualifierKey = qualifierKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorDataFile that = (SensorDataFile) o;

        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (qualifierKey != null ? !qualifierKey.equals(that.qualifierKey) : that.qualifierKey != null) return false;
        if (sample != null ? !sample.equals(that.sample) : that.sample != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        result = 31 * result + (qualifierKey != null ? qualifierKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SensorDataFile{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", hash='" + hash + '\'' +
                ", createDate=" + createDate +
                ", sample=" + sample +
                ", qualifierKey=" + qualifierKey +
                '}';
    }
}
