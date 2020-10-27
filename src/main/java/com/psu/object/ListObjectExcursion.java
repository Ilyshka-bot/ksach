package com.psu.object;

import javax.persistence.OneToMany;
import java.util.List;

public class ListObjectExcursion {
    String name;
    private List<String> objects;

    public ListObjectExcursion(){ }

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
