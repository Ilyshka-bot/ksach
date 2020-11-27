package com.psu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
INSERT INTO public.t_view_exc VALUES (1,'обычные'),(2, 'учебные'),(3, 'массовые');
*/

@Entity
@Table(name = "t_view_Exc")
public class ViewExcursion {

    @Id
    private Long id;
    private String typeName;

    public ViewExcursion(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
