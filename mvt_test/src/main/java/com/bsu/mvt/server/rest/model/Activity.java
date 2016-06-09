package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Activity implements Serializable {
    private Long id;
    private Long startTime;
    private Long duration;
    private Sample sample;
    private MotionType motionType;
    private Reglament reglament;
    private List<Extra> extraList;
    private MarkConfidence markConfidence;

    public Activity() {}

    public Activity(Long id) {
        this.id = id;
    }

    public Activity(Long startTime, Long duration, Reglament reglament, MotionType motionType) {
        this.startTime = startTime;
        this.duration = duration;
        this.reglament = reglament;
        this.motionType = motionType;
    }

    public Activity(Long startTime, Long duration, Sample sample, MotionType motionType, Reglament reglament) {
        this.startTime = startTime;
        this.duration = duration;
        this.sample = sample;
        this.motionType = motionType;
        this.reglament = reglament;
    }

    public Activity(Long id, Long startTime, Long duration, Sample sample, MotionType motionType, Reglament reglament) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.sample = sample;
        this.motionType = motionType;
        this.reglament = reglament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Reglament getReglament() {
        return reglament;
    }

    public void setReglament(Reglament reglament) {
        this.reglament = reglament;
    }

    public MotionType getMotionType() {
        return motionType;
    }

    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<Extra> extraList) {
        this.extraList = extraList;
    }

    public MarkConfidence getMarkConfidence() {
        return markConfidence;
    }

    public void setMarkConfidence(MarkConfidence markConfidence) {
        this.markConfidence = markConfidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (duration != null ? !duration.equals(activity.duration) : activity.duration != null) return false;
        if (extraList != null ? !extraList.equals(activity.extraList) : activity.extraList != null) return false;
        if (id != null ? !id.equals(activity.id) : activity.id != null) return false;
        if (markConfidence != activity.markConfidence) return false;
        if (motionType != null ? !motionType.equals(activity.motionType) : activity.motionType != null) return false;
        if (reglament != null ? !reglament.equals(activity.reglament) : activity.reglament != null) return false;
        if (sample != null ? !sample.equals(activity.sample) : activity.sample != null) return false;
        if (startTime != null ? !startTime.equals(activity.startTime) : activity.startTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (sample != null ? sample.hashCode() : 0);
        result = 31 * result + (motionType != null ? motionType.hashCode() : 0);
        result = 31 * result + (reglament != null ? reglament.hashCode() : 0);
        result = 31 * result + (extraList != null ? extraList.hashCode() : 0);
        result = 31 * result + (markConfidence != null ? markConfidence.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Activity{");
        sb.append("id=").append(id);
        sb.append(", startTime=").append(startTime);
        sb.append(", duration=").append(duration);
        sb.append(", sample=").append(sample);
        sb.append(", motionType=").append(motionType);
        sb.append(", reglament=").append(reglament);
        sb.append(", extraList=").append(extraList);
        sb.append(", markConfidence=").append(markConfidence);
        sb.append('}');
        return sb.toString();
    }
}
