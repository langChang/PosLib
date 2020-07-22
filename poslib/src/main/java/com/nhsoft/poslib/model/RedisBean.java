package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/5/16 4:53 PM
 * 此类用于：
 */
public class RedisBean {

    private String redis_key;
    private long redis_time;
    private String redis_value;

    public String getVip_id() {
        return vip_id;
    }

    public void setVip_id(String vip_id) {
        this.vip_id = vip_id;
    }

    private String vip_id;

    public String getRedis_key() {
        return redis_key;
    }

    public void setRedis_key(String redis_key) {
        this.redis_key = redis_key;
    }

    public long getRedis_time() {
        return redis_time;
    }

    public void setRedis_time(long redis_time) {
        this.redis_time = redis_time;
    }

    public String getRedis_value() {
        return redis_value;
    }

    public void setRedis_value(String redis_value) {
        this.redis_value = redis_value;
    }
}
