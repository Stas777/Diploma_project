package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VideoFile implements Serializable {
    private Long id;
    private String path;
    private String hash;
    private String description;
    private Sample sample;

    public VideoFile() {}

    public VideoFile(Long id) {
        this.id = id;
    }

    public VideoFile(String path, String hash, String description, Sample sample) {
        this.path = path;
        this.hash = hash;
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

        VideoFile videoFile = (VideoFile) o;

        if (description != null ? !description.equals(videoFile.description) : videoFile.description != null)
            return false;
        if (hash != null ? !hash.equals(videoFile.hash) : videoFile.hash != null) return false;
        if (id != null ? !id.equals(videoFile.id) : videoFile.id != null) return false;
        if (path != null ? !path.equals(videoFile.path) : videoFile.path != null) return false;
        if (sample != null ? !sample.equals(videoFile.sample) : videoFile.sample != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoFile{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", hash='" + hash + '\'' +
                ", description='" + description + '\'' +
                ", sample=" + sample +
                '}';
    }
}
