package com.bsu.mvt.server.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Reglament implements Serializable {
    private Long id;
    private String name;

    private Float maxQualificationError;
    private Float maxClassificationError;
    private Float minActivityMatch;
    private Scope scope;

    public Reglament() {}

    public Reglament(Long id) {
        this.id = id;
    }

    public Reglament(String name, Float maxQualificationError, Float maxClassificationError, Float minActivityMatch, Scope scope) {
        this.name = name;
        this.maxQualificationError = maxQualificationError;
        this.maxClassificationError = maxClassificationError;
        this.minActivityMatch = minActivityMatch;
        this.scope = scope;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMaxQualificationError() {
        return maxQualificationError;
    }

    public void setMaxQualificationError(Float maxQualificationError) {
        this.maxQualificationError = maxQualificationError;
    }

    public Float getMaxClassificationError() {
        return maxClassificationError;
    }

    public void setMaxClassificationError(Float maxClassificationError) {
        this.maxClassificationError = maxClassificationError;
    }

    public Float getMinActivityMatch() {
        return minActivityMatch;
    }

    public void setMinActivityMatch(Float minActivityMatch) {
        this.minActivityMatch = minActivityMatch;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reglament reglament = (Reglament) o;

        if (id != null ? !id.equals(reglament.id) : reglament.id != null) return false;
        if (maxClassificationError != null ? !maxClassificationError.equals(reglament.maxClassificationError) : reglament.maxClassificationError != null)
            return false;
        if (maxQualificationError != null ? !maxQualificationError.equals(reglament.maxQualificationError) : reglament.maxQualificationError != null)
            return false;
        if (minActivityMatch != null ? !minActivityMatch.equals(reglament.minActivityMatch) : reglament.minActivityMatch != null)
            return false;
        if (name != null ? !name.equals(reglament.name) : reglament.name != null) return false;
        if (scope != null ? !scope.equals(reglament.scope) : reglament.scope != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (maxQualificationError != null ? maxQualificationError.hashCode() : 0);
        result = 31 * result + (maxClassificationError != null ? maxClassificationError.hashCode() : 0);
        result = 31 * result + (minActivityMatch != null ? minActivityMatch.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reglament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxQualificationError=" + maxQualificationError +
                ", maxClassificationError=" + maxClassificationError +
                ", minActivityMatch=" + minActivityMatch +
                ", scope=" + scope +
                '}';
    }
}

