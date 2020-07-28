package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2018/12/3 10:59 AM
 * 此类用于：
 */
public class NewVersionBean {

    private String version;
    private String versionCode;
    private String url;
    private String description;
    private String url_server;
    private String level;
    private String account;
    private String minVersion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_server() {
        return url_server;
    }

    public void setUrl_server(String url_server) {
        this.url_server = url_server;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

}
