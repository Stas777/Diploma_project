package com.bsu.mvt.server.core.entity;

public class SportEntity {
    private Long id;
    private String name;
    private Long reglament_id;
    private Integer value;

    public SportEntity() {
    }

    public SportEntity(Long id, String name, Long reglamen_id) {
        this.id = id;
        this.name = name;
        this.reglament_id = reglamen_id;
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

    public Long getReglament_id() {
        return reglament_id;
    }

    public void setReglament_id(Long reglament_id) {
        this.reglament_id = reglament_id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
