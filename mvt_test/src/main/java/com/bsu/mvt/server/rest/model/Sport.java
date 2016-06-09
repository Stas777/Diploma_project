package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Sport implements Serializable {
    private Long id;
    private String name;
    private Reglament reglament;
    private Integer value;
    private List<MotionType> motionTypeList;

    public Sport() {}

    public Sport(Long id) {
        this.id = id;
    }

    public Sport(String name, Reglament reglament, Integer value, List<MotionType> motionTypeList) {
        this.name = name;
        this.reglament = reglament;
        this.value = value;
        this.motionTypeList = motionTypeList;
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

    public Reglament getReglament() {
        return reglament;
    }

    public void setReglament(Reglament reglament) {
        this.reglament = reglament;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<MotionType> getMotionTypeList() {
        return motionTypeList;
    }

    public void setMotionTypeList(List<MotionType> motionTypeList) {
        this.motionTypeList = motionTypeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sport sport = (Sport) o;

        if (id != null ? !id.equals(sport.id) : sport.id != null) return false;
        if (motionTypeList != null ? !motionTypeList.equals(sport.motionTypeList) : sport.motionTypeList != null)
            return false;
        if (name != null ? !name.equals(sport.name) : sport.name != null) return false;
        if (reglament != null ? !reglament.equals(sport.reglament) : sport.reglament != null) return false;
        if (value != null ? !value.equals(sport.value) : sport.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (reglament != null ? reglament.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (motionTypeList != null ? motionTypeList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sport{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", reglament=").append(reglament);
        sb.append(", value=").append(value);
        sb.append(", motionTypeList=").append(motionTypeList);
        sb.append('}');
        return sb.toString();
    }
}
