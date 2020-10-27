package com.psu.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


/*INSERT INTO public.t_role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_EMPLOYEE');
    */
@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    public Role() { }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String getAuthority() {
        return getName();
    }
}