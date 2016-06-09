package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QualifierKey implements Serializable {
    private Long id;
    private String name;
    private SensorLocation sensorLocation;
    private Sport sport;
    private Float signalThreshold;
    private Float extremeDelta;
    private Long minActivityDuration;

    public QualifierKey() {}

    public QualifierKey(Long id) {
        this.id = id;
    }

    public QualifierKey(String name, SensorLocation sensorLocation, Sport sport, Float signalThreshold, Float extremeDelta, Long minActivityDuration) {
        this.name = name;
        this.sensorLocation = sensorLocation;
        this.sport = sport;
        this.signalThreshold = signalThreshold;
        this.extremeDelta = extremeDelta;
        this.minActivityDuration = minActivityDuration;
    }

    public QualifierKey(Float signalThreshold, Float extremeDelta, Long minActivityDuration) {
        this.signalThreshold = signalThreshold;
        this.extremeDelta = extremeDelta;
        this.minActivityDuration = minActivityDuration;
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

    public SensorLocation getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(SensorLocation sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Float getSignalThreshold() {
        return signalThreshold;
    }

    public void setSignalThreshold(Float signalThreshold) {
        this.signalThreshold = signalThreshold;
    }

    public Float getExtremeDelta() {
        return extremeDelta;
    }

    public void setExtremeDelta(Float extremeDelta) {
        this.extremeDelta = extremeDelta;
    }

    public Long getMinActivityDuration() {
        return minActivityDuration;
    }

    public void setMinActivityDuration(Long minActivityDuration) {
        this.minActivityDuration = minActivityDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QualifierKey that = (QualifierKey) o;

        if (extremeDelta != null ? !extremeDelta.equals(that.extremeDelta) : that.extremeDelta != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (minActivityDuration != null ? !minActivityDuration.equals(that.minActivityDuration) : that.minActivityDuration != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sensorLocation != null ? !sensorLocation.equals(that.sensorLocation) : that.sensorLocation != null)
            return false;
        if (signalThreshold != null ? !signalThreshold.equals(that.signalThreshold) : that.signalThreshold != null)
            return false;
        if (sport != null ? !sport.equals(that.sport) : that.sport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sensorLocation != null ? sensorLocation.hashCode() : 0);
        result = 31 * result + (sport != null ? sport.hashCode() : 0);
        result = 31 * result + (signalThreshold != null ? signalThreshold.hashCode() : 0);
        result = 31 * result + (extremeDelta != null ? extremeDelta.hashCode() : 0);
        result = 31 * result + (minActivityDuration != null ? minActivityDuration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QualifierKey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sensorLocation=" + sensorLocation +
                ", sport=" + sport +
                ", signalThreshold=" + signalThreshold +
                ", extremeDelta=" + extremeDelta +
                ", minActivityDuration=" + minActivityDuration +
                '}';
    }
}
