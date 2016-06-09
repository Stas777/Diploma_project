package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MotionType implements Serializable {
    private Long id;
    private String name;
    private Reglament reglament;
    private Integer value;
    private List<Sport> sportList;

    public MotionType() {}

    public MotionType(Long id) {
        this.id = id;
    }

    public MotionType(String name, List<Sport> sportList, Reglament reglament, Integer value) {
        this.name = name;
        this.sportList = sportList;
        this.reglament = reglament;
        this.value = value;
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

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotionType that = (MotionType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (reglament != null ? !reglament.equals(that.reglament) : that.reglament != null) return false;
        if (sportList != null ? !sportList.equals(that.sportList) : that.sportList != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sportList != null ? sportList.hashCode() : 0);
        result = 31 * result + (reglament != null ? reglament.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MotionType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", reglament=").append(reglament);
        sb.append(", value=").append(value);
        sb.append(", sportList=").append(sportList);
        sb.append('}');
        return sb.toString();
    }
}
