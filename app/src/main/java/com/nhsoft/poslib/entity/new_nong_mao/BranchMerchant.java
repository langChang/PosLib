package com.nhsoft.poslib.entity.new_nong_mao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;


@Entity
public class BranchMerchant {


    /**
     * branch_actived : true
     * branch_code : string
     * branch_create_time : string
     * branch_id : 0
     * branch_linkman : string
     * branch_linkman_pic : string
     * branch_name : string
     * branch_num : 0
     * branch_phone : string
     * merchant_items : []
     * merchant_num : 0
     * stalls : []
     */
    @Id
    @Property(nameInDb = "BRANCH_ID")
    private String branch_id;
    private boolean branch_actived;
    private String branch_code;
    private String branch_create_time;
    private String branch_linkman;
    private String branch_linkman_pic;
    private String branch_name;
    private String branch_num;
    private String branch_phone;
    private int merchant_num;
    private String       imge_json;
    private String merchant_category;

    public String getImge_json() {
        return imge_json;
    }

    public void setImge_json(String imge_json) {
        this.imge_json = imge_json;
    }


    @Transient
    private List<String> image_urls = new ArrayList<>();

    public List<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(List<String> image_urls) {
        this.image_urls = image_urls;
    }


    @Generated(hash = 889278784)
    public BranchMerchant(String branch_id, boolean branch_actived,
            String branch_code, String branch_create_time, String branch_linkman,
            String branch_linkman_pic, String branch_name, String branch_num,
            String branch_phone, int merchant_num, String imge_json,
            String merchant_category) {
        this.branch_id = branch_id;
        this.branch_actived = branch_actived;
        this.branch_code = branch_code;
        this.branch_create_time = branch_create_time;
        this.branch_linkman = branch_linkman;
        this.branch_linkman_pic = branch_linkman_pic;
        this.branch_name = branch_name;
        this.branch_num = branch_num;
        this.branch_phone = branch_phone;
        this.merchant_num = merchant_num;
        this.imge_json = imge_json;
        this.merchant_category = merchant_category;
    }

    @Generated(hash = 1687195653)
    public BranchMerchant() {
    }
    public String getBranch_id() {
        return this.branch_id;
    }
    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }
    public boolean getBranch_actived() {
        return this.branch_actived;
    }
    public void setBranch_actived(boolean branch_actived) {
        this.branch_actived = branch_actived;
    }
    public String getBranch_code() {
        return this.branch_code;
    }
    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }
    public String getBranch_create_time() {
        return this.branch_create_time;
    }
    public void setBranch_create_time(String branch_create_time) {
        this.branch_create_time = branch_create_time;
    }
    public String getBranch_linkman() {
        return this.branch_linkman;
    }
    public void setBranch_linkman(String branch_linkman) {
        this.branch_linkman = branch_linkman;
    }
    public String getBranch_linkman_pic() {
        return this.branch_linkman_pic;
    }
    public void setBranch_linkman_pic(String branch_linkman_pic) {
        this.branch_linkman_pic = branch_linkman_pic;
    }
    public String getBranch_name() {
        return this.branch_name;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public String getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(String branch_num) {
        this.branch_num = branch_num;
    }
    public String getBranch_phone() {
        return this.branch_phone;
    }
    public void setBranch_phone(String branch_phone) {
        this.branch_phone = branch_phone;
    }
    public int getMerchant_num() {
        return this.merchant_num;
    }
    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public String getMerchant_category() {
        return this.merchant_category;
    }

    public void setMerchant_category(String merchant_category) {
        this.merchant_category = merchant_category;
    }


   
    
}
