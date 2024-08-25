package com.post.model;

import com.post.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity<Integer>  {


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }



    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
