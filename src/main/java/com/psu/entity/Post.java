package com.psu.entity;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

/*INSERT INTO public.t_post(id, name)
  VALUES (1, 'POST_GUIDE'), (2, 'POST_WORKER');
  */

@Entity
@Table(name = "t_post")
public class Post implements GrantedAuthority {

    @Id
    private Long id;

    private String name;

    public Post(){ }

    public Post(Long id){
        this.id = id;
    }

    public Post(Long id, String name) {
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
