package com.hr.mygreendao3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 吕 on 2017/11/14.
 */
@Entity
public class Pig {
    @Id
    private String id;
    private String name;
    @Generated(hash = 1151508261)
    public Pig(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 220568114)
    public Pig() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
