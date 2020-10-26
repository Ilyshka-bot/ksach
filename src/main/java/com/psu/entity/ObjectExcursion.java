package com.psu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
insert into t_object (id, name, type,price,time_duration) values (1, 'VR', 'hard',20, 30),
(2, 'Graphic', 'hard',30, 40),(3, 'Labirinth', 'easy',40, 20),(4, 'Storm', 'easy',50, 30),
(5, 'Numbers', 'hard',40, 20);
 */

@Entity
@Table(name = "t_object")
public class ObjectExcursion {
    @Id
    private Long id;

    private String name;
    private String type;
    private Long price;
    private String timeDuration;

    public ObjectExcursion(){ }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

}
