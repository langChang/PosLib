package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SystemPrint {

    @Id
    private String SystemPrintID = "SYSTEM_PRINT";
    private int logo;//企业logo  (本地图片)
    private String logoName;//企业名称 1
    private String titleSlogan;//打印头部 企业宣传语 2
    private int selfSlogan;//企业社交媒体二维
    private String footSlogan;//打印底部 企业宣传语 3
    private String itemSelf;//商家自定义信息 4

    private boolean isSlogan;//是否启用 显示企业宣传语
    private boolean isfoot;//是否启用 显示企业社交媒体二维码及宣传语
    private boolean isSelf;//是否启用 显示商家自定义信息



    @Generated(hash = 1369140399)
    public SystemPrint(String SystemPrintID, int logo, String logoName,
            String titleSlogan, int selfSlogan, String footSlogan, String itemSelf,
            boolean isSlogan, boolean isfoot, boolean isSelf) {
        this.SystemPrintID = SystemPrintID;
        this.logo = logo;
        this.logoName = logoName;
        this.titleSlogan = titleSlogan;
        this.selfSlogan = selfSlogan;
        this.footSlogan = footSlogan;
        this.itemSelf = itemSelf;
        this.isSlogan = isSlogan;
        this.isfoot = isfoot;
        this.isSelf = isSelf;
    }
    @Generated(hash = 689485742)
    public SystemPrint() {
    }


    public int getLogo() {
        return this.logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public String getLogoName() {
        return this.logoName;
    }
    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
    public String getTitleSlogan() {
        return this.titleSlogan;
    }
    public void setTitleSlogan(String titleSlogan) {
        this.titleSlogan = titleSlogan;
    }
    public int getSelfSlogan() {
        return this.selfSlogan;
    }
    public void setSelfSlogan(int selfSlogan) {
        this.selfSlogan = selfSlogan;
    }
    public String getFootSlogan() {
        return this.footSlogan;
    }
    public void setFootSlogan(String footSlogan) {
        this.footSlogan = footSlogan;
    }
    public String getItemSelf() {
        return this.itemSelf;
    }
    public void setItemSelf(String itemSelf) {
        this.itemSelf = itemSelf;
    }
    public boolean getIsSlogan() {
        return this.isSlogan;
    }
    public void setIsSlogan(boolean isSlogan) {
        this.isSlogan = isSlogan;
    }
    public boolean getIsfoot() {
        return this.isfoot;
    }
    public void setIsfoot(boolean isfoot) {
        this.isfoot = isfoot;
    }
    public boolean getIsSelf() {
        return this.isSelf;
    }
    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
    public String getSystemPrintID() {
        return this.SystemPrintID;
    }
    public void setSystemPrintID(String SystemPrintID) {
        this.SystemPrintID = SystemPrintID;
    }




}
