package com.psu.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "t_graphic_employee")
public class GraphicEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    private String dateStart;

    private String timeStart;
    private String timeEnd;

    public GraphicEmployee(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
