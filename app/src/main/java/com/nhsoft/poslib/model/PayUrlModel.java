package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2019-06-05 10:45
 * 此类用于：
 */
public class PayUrlModel {

    /**
     * centerId : 4344
     * centerName :
     * groupId :
     * bookProxyName : MYSQL51共享服务器
     * bookProxyPort : 80
     * bookProxyPath : amazonCenter
     * bookProxyServer : 10.142.71.77
     * bookProxyRemoteServer : 116.62.106.114
     * bookProxyUpdateServer : 120.55.206.155
     * bookProxyUpdateChannel : AMANorth
     * bookProxyUpdatePortStart : 29000
     * bookProxyUpdatePortEnd : 29003
     * bookProxyRegion : 杭州
     * bookProxySynchServer : 10.29.112.213
     * bookProxySynchRemoteServer : 47.98.67.165
     * bookProxyCardServer : 10.29.112.213
     * bookProxyCardRemoteServer : 116.62.106.114
     * bookProxyRdsName :
     * bookProxyWebServer : erp.nhsoft.cn
     * bookProxyPayServer : 120.55.30.194
     * bookProxyTempChannel :
     * bookProxyTempChannelExpire :
     * bookProxyReportServer :
     * bookProxyId : 8a71d0c04cc5da0e014ccfb46eab0006
     * url : http://10.142.71.77:80/amazonCenter
     * bookProxyChainFlag :
     * hasBookDbSource :
     * hasChangeDbSource :
     * cardUrl : http://10.29.112.213:80/amazonCenter
     * serverUrl : server_url=10.142.71.77;server_port80;server_endpoint=amazonCenter;
     * updateRemoteUrl : 120.55.206.155,29000,29003,,,AMANorth
     * proxyRemoteUrl : http://116.62.106.114:80/amazonCenter
     * remoteUrl : http://10.142.71.77:80/amazonCenter/remoting/
     */

    private int centerId;
    private String centerName;
    private String groupId;
    private String bookProxyName;
    private int    bookProxyPort;
    private String bookProxyPath;
    private String bookProxyServer;
    private String bookProxyRemoteServer;
    private String bookProxyUpdateServer;
    private String bookProxyUpdateChannel;
    private int    bookProxyUpdatePortStart;
    private int    bookProxyUpdatePortEnd;
    private String bookProxyRegion;
    private String bookProxySynchServer;
    private String bookProxySynchRemoteServer;
    private String bookProxyCardServer;
    private String bookProxyCardRemoteServer;
    private String bookProxyRdsName;
    private String bookProxyWebServer;
    private String bookProxyPayServer;
    private String bookProxyTempChannel;
    private String bookProxyTempChannelExpire;
    private String bookProxyReportServer;
    private String bookProxyId;
    private String url;
    private String bookProxyChainFlag;
    private String hasBookDbSource;
    private String hasChangeDbSource;
    private String cardUrl;
    private String serverUrl;
    private String updateRemoteUrl;
    private String proxyRemoteUrl;
    private String remoteUrl;
    private String bookProxyAndroidServer;
    private String bookProxyShared; //是否是共享


    public String isBookProxyShared() {
        return bookProxyShared;
    }

    public void setBookProxyShared(String bookProxyShared) {
        this.bookProxyShared = bookProxyShared;
    }


    public String getBookProxyMnsServer() {
        return bookProxyMnsServer;
    }

    public void setBookProxyMnsServer(String bookProxyMnsServer) {
        this.bookProxyMnsServer = bookProxyMnsServer;
    }

    private String bookProxyMnsServer;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBookProxyName() {
        return bookProxyName;
    }

    public void setBookProxyName(String bookProxyName) {
        this.bookProxyName = bookProxyName;
    }

    public int getBookProxyPort() {
        return bookProxyPort;
    }

    public void setBookProxyPort(int bookProxyPort) {
        this.bookProxyPort = bookProxyPort;
    }

    public String getBookProxyPath() {
        return bookProxyPath;
    }

    public void setBookProxyPath(String bookProxyPath) {
        this.bookProxyPath = bookProxyPath;
    }

    public String getBookProxyServer() {
        return bookProxyServer;
    }

    public void setBookProxyServer(String bookProxyServer) {
        this.bookProxyServer = bookProxyServer;
    }

    public String getBookProxyRemoteServer() {
        return bookProxyRemoteServer;
    }

    public void setBookProxyRemoteServer(String bookProxyRemoteServer) {
        this.bookProxyRemoteServer = bookProxyRemoteServer;
    }

    public String getBookProxyUpdateServer() {
        return bookProxyUpdateServer;
    }

    public void setBookProxyUpdateServer(String bookProxyUpdateServer) {
        this.bookProxyUpdateServer = bookProxyUpdateServer;
    }

    public String getBookProxyUpdateChannel() {
        return bookProxyUpdateChannel;
    }

    public void setBookProxyUpdateChannel(String bookProxyUpdateChannel) {
        this.bookProxyUpdateChannel = bookProxyUpdateChannel;
    }

    public int getBookProxyUpdatePortStart() {
        return bookProxyUpdatePortStart;
    }

    public void setBookProxyUpdatePortStart(int bookProxyUpdatePortStart) {
        this.bookProxyUpdatePortStart = bookProxyUpdatePortStart;
    }

    public int getBookProxyUpdatePortEnd() {
        return bookProxyUpdatePortEnd;
    }

    public void setBookProxyUpdatePortEnd(int bookProxyUpdatePortEnd) {
        this.bookProxyUpdatePortEnd = bookProxyUpdatePortEnd;
    }

    public String getBookProxyRegion() {
        return bookProxyRegion;
    }

    public void setBookProxyRegion(String bookProxyRegion) {
        this.bookProxyRegion = bookProxyRegion;
    }

    public String getBookProxySynchServer() {
        return bookProxySynchServer;
    }

    public void setBookProxySynchServer(String bookProxySynchServer) {
        this.bookProxySynchServer = bookProxySynchServer;
    }

    public String getBookProxySynchRemoteServer() {
        return bookProxySynchRemoteServer;
    }

    public void setBookProxySynchRemoteServer(String bookProxySynchRemoteServer) {
        this.bookProxySynchRemoteServer = bookProxySynchRemoteServer;
    }

    public String getBookProxyCardServer() {
        return bookProxyCardServer;
    }

    public void setBookProxyCardServer(String bookProxyCardServer) {
        this.bookProxyCardServer = bookProxyCardServer;
    }

    public String getBookProxyCardRemoteServer() {
        return bookProxyCardRemoteServer;
    }

    public void setBookProxyCardRemoteServer(String bookProxyCardRemoteServer) {
        this.bookProxyCardRemoteServer = bookProxyCardRemoteServer;
    }

    public String getBookProxyRdsName() {
        return bookProxyRdsName;
    }

    public void setBookProxyRdsName(String bookProxyRdsName) {
        this.bookProxyRdsName = bookProxyRdsName;
    }

    public String getBookProxyWebServer() {
        return bookProxyWebServer;
    }

    public void setBookProxyWebServer(String bookProxyWebServer) {
        this.bookProxyWebServer = bookProxyWebServer;
    }

    public String getBookProxyPayServer() {
        return bookProxyPayServer;
    }

    public void setBookProxyPayServer(String bookProxyPayServer) {
        this.bookProxyPayServer = bookProxyPayServer;
    }

    public String getBookProxyTempChannel() {
        return bookProxyTempChannel;
    }

    public void setBookProxyTempChannel(String bookProxyTempChannel) {
        this.bookProxyTempChannel = bookProxyTempChannel;
    }

    public String getBookProxyTempChannelExpire() {
        return bookProxyTempChannelExpire;
    }

    public void setBookProxyTempChannelExpire(String bookProxyTempChannelExpire) {
        this.bookProxyTempChannelExpire = bookProxyTempChannelExpire;
    }

    public String getBookProxyReportServer() {
        return bookProxyReportServer;
    }

    public void setBookProxyReportServer(String bookProxyReportServer) {
        this.bookProxyReportServer = bookProxyReportServer;
    }

    public String getBookProxyId() {
        return bookProxyId;
    }

    public void setBookProxyId(String bookProxyId) {
        this.bookProxyId = bookProxyId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookProxyChainFlag() {
        return bookProxyChainFlag;
    }

    public void setBookProxyChainFlag(String bookProxyChainFlag) {
        this.bookProxyChainFlag = bookProxyChainFlag;
    }

    public String getHasBookDbSource() {
        return hasBookDbSource;
    }

    public void setHasBookDbSource(String hasBookDbSource) {
        this.hasBookDbSource = hasBookDbSource;
    }

    public String getHasChangeDbSource() {
        return hasChangeDbSource;
    }

    public void setHasChangeDbSource(String hasChangeDbSource) {
        this.hasChangeDbSource = hasChangeDbSource;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUpdateRemoteUrl() {
        return updateRemoteUrl;
    }

    public void setUpdateRemoteUrl(String updateRemoteUrl) {
        this.updateRemoteUrl = updateRemoteUrl;
    }

    public String getProxyRemoteUrl() {
        return proxyRemoteUrl;
    }

    public void setProxyRemoteUrl(String proxyRemoteUrl) {
        this.proxyRemoteUrl = proxyRemoteUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getBookProxyAndroidServer() {
        return bookProxyAndroidServer;
    }

    public void setBookProxyAndroidServer(String bookProxyAndroidServer) {
        this.bookProxyAndroidServer = bookProxyAndroidServer;
    }

}
