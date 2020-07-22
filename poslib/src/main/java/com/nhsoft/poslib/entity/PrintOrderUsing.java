package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用于小票 应用订单小票模板
 */
@Entity
public class PrintOrderUsing {

    @Id
    private String id;// 不同模块下该属性不同，以保存成不同的数据（同一模块下只保存一个正在使用的模板）
    private String print_using_name;//正在使用的模板的名字
    private String membership;// 隶属于那个模块下
    private String end_operator_time;//最后操作时间
    private int version_code;//

    @Generated(hash = 1106161549)
    public PrintOrderUsing(String id, String print_using_name, String membership,
            String end_operator_time, int version_code) {
        this.id = id;
        this.print_using_name = print_using_name;
        this.membership = membership;
        this.end_operator_time = end_operator_time;
        this.version_code = version_code;
    }

    @Generated(hash = 605470070)
    public PrintOrderUsing() {
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrint_using_name() {
        return print_using_name;
    }

    public void setPrint_using_name(String print_using_name) {
        this.print_using_name = print_using_name;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getEnd_operator_time() {
        return end_operator_time;
    }

    public void setEnd_operator_time(String end_operator_time) {
        this.end_operator_time = end_operator_time;
    }
}
