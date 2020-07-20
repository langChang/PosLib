package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class Aggregation {
    /**
     *聚合
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String app_id;
    private String secret;

    @Generated(hash = 1618524862)
    public Aggregation(Long id, String app_id, String secret) {
        this.id = id;
        this.app_id = app_id;
        this.secret = secret;
    }

    @Generated(hash = 238235527)
    public Aggregation() {
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
