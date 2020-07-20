package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/15 6:16 PM
 * 此类用于：做一个demo便于参照
 */
@Entity
public class DemoEntity {
    @Id(autoincrement = true)
    private Long id;
    private String demo;
    @Generated(hash = 1577005768)
    public DemoEntity(Long id, String demo) {
        this.id = id;
        this.demo = demo;
    }
    @Generated(hash = 1979632871)
    public DemoEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDemo() {
        return this.demo;
    }
    public void setDemo(String demo) {
        this.demo = demo;
    }

}
