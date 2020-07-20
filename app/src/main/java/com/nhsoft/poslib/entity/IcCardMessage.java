package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IcCardMessage {

    /**
     *IC卡 信息
     *
     * branch_iC : true
     * reader_a : string
     * reader_b : string
     * reader_c : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private boolean branch_iC;
    private boolean branch_iD;
    private String reader_a;
    private String reader_b;
    private String reader_c;


    @Generated(hash = 1904670526)
    public IcCardMessage(Long id, boolean branch_iC, boolean branch_iD,
            String reader_a, String reader_b, String reader_c) {
        this.id = id;
        this.branch_iC = branch_iC;
        this.branch_iD = branch_iD;
        this.reader_a = reader_a;
        this.reader_b = reader_b;
        this.reader_c = reader_c;
    }

    @Generated(hash = 780531576)
    public IcCardMessage() {
    }


    public boolean isBranch_iD() {
        return branch_iD;
    }

    public void setBranch_iD(boolean branch_iD) {
        this.branch_iD = branch_iD;
    }

    public boolean isBranch_iC() {
        return branch_iC;
    }

    public void setBranch_iC(boolean branch_iC) {
        this.branch_iC = branch_iC;
    }

    public String getReader_a() {
        return reader_a;
    }

    public void setReader_a(String reader_a) {
        this.reader_a = reader_a;
    }

    public String getReader_b() {
        return reader_b;
    }

    public void setReader_b(String reader_b) {
        this.reader_b = reader_b;
    }

    public String getReader_c() {
        return reader_c;
    }

    public void setReader_c(String reader_c) {
        this.reader_c = reader_c;
    }

    public boolean getBranch_iC() {
        return this.branch_iC;
    }

    public boolean getBranch_iD() {
        return this.branch_iD;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
