package com.bsu.mvt.server.rest.model;

import java.io.Serializable;
import java.util.Set;

public class Scope implements Serializable {
    private Set<Type> values;

    public Scope() {}

/*
    public Scope(Type ... types) {
        setValues(types);
    }
*/

    public Scope(Set<Type> values) {
        this.values = values;
    }

    public Set<Type> getValues() {
        return values;
    }

    public void setValues(Set<Type> values) {
        this.values = values;
    }

    /*
        public void setValues(Type ... types) {
            values = new HashSet<>(Arrays.asList(types));
        }
    */
    public enum Type implements Serializable {
        SPORT, SAMPLE, ACTIVITY, MOTION_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scope scope = (Scope) o;

        if (values != null ? !values.equals(scope.values) : scope.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Scope{");
        sb.append("values=").append(values);
        sb.append('}');
        return sb.toString();
    }
}
