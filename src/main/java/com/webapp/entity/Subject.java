package com.webapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
    private int id;
    private String name;

    public Subject(){}
    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Subject(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Subject subject = (Subject) o;
        return id == subject.id && Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
