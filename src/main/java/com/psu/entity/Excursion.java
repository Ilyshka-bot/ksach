package com.psu.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
/*
INSERT INTO public.t_excursion(
	id, description, name, price, "time", view_excursion_id)
	VALUES (1, 'описание1', 'экскурсия1', '100', '1 час', 1),
	 (2, 'описание2', 'экскурсия2', '200', '1.5 часа', 2),
	 (3, 'описание3', 'экскурсия3', '300', '2 часа', 3);
* */
@Entity
@Table(name = "t_excursion")
public class Excursion {
    @Id
    private Long id;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne
    private ViewExcursion viewExcursion;

    private String name;
    private String description;
    private String price;
    private String time;

    public Excursion(){ }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public ViewExcursion getViewExcursion() {
        return viewExcursion;
    }

    public void setViewExcursion(ViewExcursion viewExcursion) {
        this.viewExcursion = viewExcursion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
