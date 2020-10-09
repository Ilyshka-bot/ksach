package com.psu.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "t_client")
public class Client implements Serializable{
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportData;

    public Client(){ }

    public Client(String passport){
        passportData = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

}