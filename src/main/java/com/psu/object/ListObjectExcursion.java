package com.psu.object;

import javax.persistence.OneToMany;

public class ListObjectExcursion {
    String name;
    private String[] objects;

    public ListObjectExcursion(){ }

    public String[] getObjects() {
        return objects;
    }

    public void setObjects(String[] objects) {
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
