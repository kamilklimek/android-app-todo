package com.locatetasks.ui.main.model;

import java.util.Objects;

abstract class AbstractModel {
    private Long id;
    private final String name;

    AbstractModel( String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public final Long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractModel that = (AbstractModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
