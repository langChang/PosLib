package com.nhsoft.poslib.entity.nongmao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 农贸
 *
 * 档口
 */
@Entity
public class Stall {
    /**
     * branch_num : 0
     * stall_category : string
     * stall_code : string
     * stall_del_tag : true
     * stall_memo : string
     * stall_name : string
     * stall_num : 0
     * storehouse_num : 0
     * system_book_code : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String branch_num;
    private String  stall_category;
    private String  stall_code;
    private boolean stall_del_tag;
    private String  stall_memo;
    private String  stall_name;
    private int     stall_num;
    private int     storehouse_num;
    private String  system_book_code;


    @Generated(hash = 1858876038)
    public Stall(Long id, String branch_num, String stall_category,
            String stall_code, boolean stall_del_tag, String stall_memo,
            String stall_name, int stall_num, int storehouse_num,
            String system_book_code) {
        this.id = id;
        this.branch_num = branch_num;
        this.stall_category = stall_category;
        this.stall_code = stall_code;
        this.stall_del_tag = stall_del_tag;
        this.stall_memo = stall_memo;
        this.stall_name = stall_name;
        this.stall_num = stall_num;
        this.storehouse_num = storehouse_num;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 708206599)
    public Stall() {
    }


    public String getStall_category() {
        return stall_category;
    }

    public void setStall_category(String stall_category) {
        this.stall_category = stall_category;
    }

    public String getStall_code() {
        return stall_code;
    }

    public void setStall_code(String stall_code) {
        this.stall_code = stall_code;
    }

    public boolean isStall_del_tag() {
        return stall_del_tag;
    }

    public void setStall_del_tag(boolean stall_del_tag) {
        this.stall_del_tag = stall_del_tag;
    }

    public String getStall_memo() {
        return stall_memo;
    }

    public void setStall_memo(String stall_memo) {
        this.stall_memo = stall_memo;
    }

    public String getStall_name() {
        return stall_name;
    }

    public void setStall_name(String stall_name) {
        this.stall_name = stall_name;
    }

    public int getStall_num() {
        return stall_num;
    }

    public void setStall_num(int stall_num) {
        this.stall_num = stall_num;
    }

    public int getStorehouse_num() {
        return storehouse_num;
    }

    public void setStorehouse_num(int storehouse_num) {
        this.storehouse_num = storehouse_num;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getStall_del_tag() {
        return this.stall_del_tag;
    }

    public String getBranch_num() {
        return this.branch_num;
    }

    public void setBranch_num(String branch_num) {
        this.branch_num = branch_num;
    }
}
