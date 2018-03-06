package com.hr.mygreendao3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 吕 on 2017/11/10.
 */
@Entity
public class Dog {
    @Id(autoincrement = true)
    private Long id;
    private String name;

    @Generated(hash = 433044218)
    public Dog(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2001499333)
    public Dog() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
