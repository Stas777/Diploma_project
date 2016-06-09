package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class  Sample implements Serializable {
    private Long id;
    private String description;
    private Reglament reglament;
    private Sport sport;
    private Usage usage;
    private PlayerLevel playerLevel;
    private VideoFile videoFile;
    private List<SensorDataFile> dataFileList;
    private List<Activity> activityList;
    private List<Extra> extraList;
    private List<ExtraFile> extraFileList;

    public Sample() {}

    public Sample(Long id) {
        this.id = id;
    }

    public Sample(String description, Reglament reglament, Sport sport, Usage usage) {
        this.description = description;
        this.reglament = reglament;
        this.sport = sport;
        this.usage = usage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reglament getReglament() {
        return reglament;
    }

    public void setReglament(Reglament reglament) {
        this.reglament = reglament;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public VideoFile getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public List<SensorDataFile> getDataFileList() {
        return dataFileList;
    }

    public void setDataFileList(List<SensorDataFile> dataFileList) {
        this.dataFileList = dataFileList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    public void setExtraList(List<Extra> extraList) {
        this.extraList = extraList;
    }

    public PlayerLevel getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(PlayerLevel playerLevel) {
        this.playerLevel = playerLevel;
    }

    public List<ExtraFile> getExtraFileList() {
        return extraFileList;
    }

    public void setExtraFileList(List<ExtraFile> extraFileList) {
        this.extraFileList = extraFileList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sample sample = (Sample) o;

        if (activityList != null ? !activityList.equals(sample.activityList) : sample.activityList != null)
            return false;
        if (dataFileList != null ? !dataFileList.equals(sample.dataFileList) : sample.dataFileList != null)
            return false;
        if (description != null ? !description.equals(sample.description) : sample.description != null) return false;
        if (extraList != null ? !extraList.equals(sample.extraList) : sample.extraList != null) return false;
        if (extraFileList != null ? !extraFileList.equals(sample.extraFileList) : sample.extraFileList != null) return false;
        if (id != null ? !id.equals(sample.id) : sample.id != null) return false;
        if (playerLevel != sample.playerLevel) return false;
        if (reglament != null ? !reglament.equals(sample.reglament) : sample.reglament != null) return false;
        if (sport != null ? !sport.equals(sample.sport) : sample.sport != null) return false;
        if (usage != sample.usage) return false;
        if (videoFile != null ? !videoFile.equals(sample.videoFile) : sample.videoFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reglament != null ? reglament.hashCode() : 0);
        result = 31 * result + (sport != null ? sport.hashCode() : 0);
        result = 31 * result + (usage != null ? usage.hashCode() : 0);
        result = 31 * result + (videoFile != null ? videoFile.hashCode() : 0);
        result = 31 * result + (dataFileList != null ? dataFileList.hashCode() : 0);
        result = 31 * result + (activityList != null ? activityList.hashCode() : 0);
        result = 31 * result + (extraList != null ? extraList.hashCode() : 0);
        result = 31 * result + (extraFileList != null ? extraFileList.hashCode() : 0);
        result = 31 * result + (playerLevel != null ? playerLevel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sample{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", reglament=").append(reglament);
        sb.append(", sport=").append(sport);
        sb.append(", usage=").append(usage);
        sb.append(", videoFile=").append(videoFile);
        sb.append(", dataFileList=").append(dataFileList);
        sb.append(", activityList=").append(activityList);
        sb.append(", extraList=").append(extraList);
        sb.append(", extraFileList=").append(extraFileList);
        sb.append(", playerLevel=").append(playerLevel);
        sb.append('}');
        return sb.toString();
    }
}
