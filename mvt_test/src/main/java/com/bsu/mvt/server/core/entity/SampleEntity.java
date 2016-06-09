package com.bsu.mvt.server.core.entity;

import com.bsu.mvt.server.rest.model.*;

public class SampleEntity {
    private Long id;
    private String description;
    private Usage usage; // TODO: refactor make it string like player_level, use ENUM only in services
    private Long reglament_id;
    private Long sport_id;
    private String player_level;

    public SampleEntity() {}

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

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Long getReglament_id() {
        return reglament_id;
    }

    public void setReglament_id(Long reglament_id) {
        this.reglament_id = reglament_id;
    }

    public Long getSport_id() {
        return sport_id;
    }

    public void setSport_id(Long sport_id) {
        this.sport_id = sport_id;
    }

    public String getPlayer_level() {
        return player_level;
    }

    public void setPlayer_level(String player_level) {
        this.player_level = player_level;
    }
}
