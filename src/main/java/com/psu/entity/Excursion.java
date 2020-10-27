package com.psu.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/*
INSERT INTO public.t_excursion(
	 description, name, price, "time", view_excursion_id)
	VALUES ( 'описание1', 'экскурсия1', 100, '1:00', 1),
	 ( 'описание2', 'экскурсия2', 200, '1:30', 2),
	 ( 'описание3', 'экскурсия3', 300, '2:00', 3);
* */
@Entity
@Table(name = "t_excursion")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY)
    private Set<ObjectExcursion> objectExcursion;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    private ViewExcursion viewExcursion;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    private String name;
    private String description;
    private Long price;
    private String time;

    public Excursion(){ }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<ObjectExcursion> getObjectExcursion() {
        return objectExcursion;
    }

    public void setObjectExcursion(Set<ObjectExcursion> objectExcursion) {
        this.objectExcursion = objectExcursion;
    }
}
