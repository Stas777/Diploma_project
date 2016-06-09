package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExtraFile implements Serializable {
    private Long id;
    private String path;
    private String hash;
    private String displayName;
    private String description;
    private Sample sample;


    public ExtraFile() {}

    public ExtraFile(Long id) {
        this.id = id;
    }

    public ExtraFile(String path, String hash, String displayName, String description, Sample sample) {
        this.path = path;
        this.hash = hash;
        this.displayName = displayName;
        this.description = description;
        this.sample = sample;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraFile extraFile = (ExtraFile) o;

        if (id != null ? !id.equals(extraFile.id) : extraFile.id != null) return false;
        if (path != null ? !path.equals(extraFile.path) : extraFile.path != null) return false;
        if (hash != null ? !hash.equals(extraFile.hash) : extraFile.hash != null) return false;
        if (displayName != null ? !displayName.equals(extraFile.displayName) : extraFile.displayName != null)
            return false;
        if (description != null ? !description.equals(extraFile.description) : extraFile.description != null)
            return false;
        return !(sample != null ? !sample.equals(extraFile.sample) : extraFile.sample != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExtraFile{");
        sb.append("id=").append(id);
        sb.append(", path='").append(path).append('\'');
        sb.append(", hash='").append(hash).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", sample=").append(sample);
        sb.append('}');
        return sb.toString();
    }


}
