package com.nhsoft.poslib.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 云服务时间和证书到期时间
 */
@Entity
public class YunServiceDays {
    /**
     * {"code":"0","msg":"请求成功","yunServiceDays":1178,"authDateTo":"2022-09-30 14:55:14","ecsDateTo":"2022-09-30 14:55:14"}
     */

    @Id
    @Property(nameInDb = "ID")
    private String id;
    private int yunServiceDays;//云服务时间
    private String authDateTo;//证书到期时间
    private Integer licenseFileVersion;

    public Integer getLicenseFileVersion() {
        return licenseFileVersion;
    }

    public void setLicenseFileVersion(Integer licenseFileVersion) {
        this.licenseFileVersion = licenseFileVersion;
    }

    @Generated(hash = 579497335)
    public YunServiceDays(String id, int yunServiceDays, String authDateTo, Integer licenseFileVersion) {
        this.id = id;
        this.yunServiceDays = yunServiceDays;
        this.authDateTo = authDateTo;
        this.licenseFileVersion = licenseFileVersion;
    }

    @Generated(hash = 1137464561)
    public YunServiceDays() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getYunServiceDays() {
        return this.yunServiceDays;
    }
    public void setYunServiceDays(int yunServiceDays) {
        this.yunServiceDays = yunServiceDays;
    }
    public String getAuthDateTo() {
        return this.authDateTo;
    }
    public void setAuthDateTo(String authDateTo) {
        this.authDateTo = authDateTo;
    }

}
