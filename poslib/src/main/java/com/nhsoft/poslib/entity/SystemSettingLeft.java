package com.nhsoft.poslib.entity;


import java.util.List;

/**
 * 小票中心库 中间行 bean
 */
public class SystemSettingLeft {

    private String name;
    private String model_type;
    private List<SystemSettingPrint>list;

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemSettingPrint> getList() {
        return list;
    }

    public void setList(List<SystemSettingPrint> list) {
        this.list = list;
    }
}
