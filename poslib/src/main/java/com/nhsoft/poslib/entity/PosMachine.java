package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class PosMachine {
    /**
     * 获取所有注册POS机
     */
    /**
     * branch_num : 1
     * pos_machine_num : 168
     * storehouse_num : 444400027
     * pos_machine_name : 喜临门蔬果（熟食）专卖演示帐套POS19
     * pos_machine_terminal_id : 4C-32-75-8B-C7-2D
     * pos_machine_memo :
     * pos_machine_enabled : false
     * pos_machine_sequence : 19
     */

    private int branch_num;
    @Id
    @Property(nameInDb = "POS_MACHINE_NUM")
    private Long pos_machine_num;
    private int storehouse_num;
    private String pos_machine_name;
    private String pos_machine_terminal_id;
    private String pos_machine_memo;
    private boolean pos_machine_enabled;
    private int pos_machine_sequence;

    private Integer stall_num;
    private Integer merchant_num;
    private String merchant_branch_id;

    @Generated(hash = 600764993)
    public PosMachine(int branch_num, Long pos_machine_num, int storehouse_num,
            String pos_machine_name, String pos_machine_terminal_id,
            String pos_machine_memo, boolean pos_machine_enabled,
            int pos_machine_sequence, Integer stall_num, Integer merchant_num,
            String merchant_branch_id) {
        this.branch_num = branch_num;
        this.pos_machine_num = pos_machine_num;
        this.storehouse_num = storehouse_num;
        this.pos_machine_name = pos_machine_name;
        this.pos_machine_terminal_id = pos_machine_terminal_id;
        this.pos_machine_memo = pos_machine_memo;
        this.pos_machine_enabled = pos_machine_enabled;
        this.pos_machine_sequence = pos_machine_sequence;
        this.stall_num = stall_num;
        this.merchant_num = merchant_num;
        this.merchant_branch_id = merchant_branch_id;
    }

    @Generated(hash = 1072705010)
    public PosMachine() {
    }

    public Integer getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(Integer merchant_num) {
        this.merchant_num = merchant_num;
    }

    public Integer getStall_num() {
        return stall_num;
    }

    public void setStall_num(Integer stall_num) {
        this.stall_num = stall_num;
    }

    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public Long getPos_machine_num() {
        return this.pos_machine_num;
    }
    public void setPos_machine_num(Long pos_machine_num) {
        this.pos_machine_num = pos_machine_num;
    }
    public int getStorehouse_num() {
        return this.storehouse_num;
    }
    public void setStorehouse_num(int storehouse_num) {
        this.storehouse_num = storehouse_num;
    }
    public String getPos_machine_name() {
        return this.pos_machine_name;
    }
    public void setPos_machine_name(String pos_machine_name) {
        this.pos_machine_name = pos_machine_name;
    }
    public String getPos_machine_terminal_id() {
        return this.pos_machine_terminal_id;
    }
    public void setPos_machine_terminal_id(String pos_machine_terminal_id) {
        this.pos_machine_terminal_id = pos_machine_terminal_id;
    }
    public String getPos_machine_memo() {
        return this.pos_machine_memo;
    }
    public void setPos_machine_memo(String pos_machine_memo) {
        this.pos_machine_memo = pos_machine_memo;
    }
    public boolean getPos_machine_enabled() {
        return this.pos_machine_enabled;
    }
    public void setPos_machine_enabled(boolean pos_machine_enabled) {
        this.pos_machine_enabled = pos_machine_enabled;
    }
    public int getPos_machine_sequence() {
        return this.pos_machine_sequence;
    }
    public void setPos_machine_sequence(int pos_machine_sequence) {
        this.pos_machine_sequence = pos_machine_sequence;
    }

    public String getMerchant_branch_id() {
        return this.merchant_branch_id;
    }

    public void setMerchant_branch_id(String merchant_branch_id) {
        this.merchant_branch_id = merchant_branch_id;
    }
   
}
