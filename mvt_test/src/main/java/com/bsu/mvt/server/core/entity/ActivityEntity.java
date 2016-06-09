package com.bsu.mvt.server.core.entity;

public class ActivityEntity {
    private Long id;
    private Long start_time;
    private Long duration;
    private Long sample_id;
    private Long motion_type_id;
    private Long reglament_id;
    private String mark_confidence;

    public ActivityEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSample_id() {
        return sample_id;
    }

    public void setSample_id(Long sample_id) {
        this.sample_id = sample_id;
    }

    public Long getMotion_type_id() {
        return motion_type_id;
    }

    public void setMotion_type_id(Long motion_type_id) {
        this.motion_type_id = motion_type_id;
    }

    public Long getReglament_id() {
        return reglament_id;
    }

    public void setReglament_id(Long reglament_id) {
        this.reglament_id = reglament_id;
    }

    public String getMark_confidence() {
        return mark_confidence;
    }

    public void setMark_confidence(String mark_confidence) {
        this.mark_confidence = mark_confidence;
    }
}
