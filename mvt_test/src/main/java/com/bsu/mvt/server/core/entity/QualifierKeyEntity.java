package com.bsu.mvt.server.core.entity;

public class QualifierKeyEntity {
    private Long id;
    private String name;
    private Float signal_threshold;
    private Float extreme_delta;
    private Long min_activity_duration;
    private Long sensor_location_id;
    private Long sport_id;

    public QualifierKeyEntity() {}

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

    public Float getSignal_threshold() {
        return signal_threshold;
    }

    public void setSignal_threshold(Float signal_threshold) {
        this.signal_threshold = signal_threshold;
    }

    public Float getExtreme_delta() {
        return extreme_delta;
    }

    public void setExtreme_delta(Float extreme_delta) {
        this.extreme_delta = extreme_delta;
    }

    public Long getMin_activity_duration() {
        return min_activity_duration;
    }

    public void setMin_activity_duration(Long min_activity_duration) {
        this.min_activity_duration = min_activity_duration;
    }

    public Long getSensor_location_id() {
        return sensor_location_id;
    }

    public void setSensor_location_id(Long sensor_location_id) {
        this.sensor_location_id = sensor_location_id;
    }

    public Long getSport_id() {
        return sport_id;
    }

    public void setSport_id(Long sport_id) {
        this.sport_id = sport_id;
    }
}
