package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class SystemBook {
    /**
     * book_name : 喜临门蔬果（熟食）专卖演示帐套
     * book_actived : true
     * system_book_code : 4444
     */

    @Id
    @Property(nameInDb = "SYSTEM_BOOK_CODE")
    private String system_book_code;
    private String book_name;
    private boolean book_actived;

    private String book_scope_id;

    public String getBook_scope_id() {
        return book_scope_id;
    }

    public void setBook_scope_id(String book_scope_id) {
        this.book_scope_id = book_scope_id;
    }
    @Generated(hash = 1605127670)
    public SystemBook(String system_book_code, String book_name,
            boolean book_actived, String book_scope_id) {
        this.system_book_code = system_book_code;
        this.book_name = book_name;
        this.book_actived = book_actived;
        this.book_scope_id = book_scope_id;
    }

    @Generated(hash = 638970798)
    public SystemBook() {
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public String getBook_name() {
        return this.book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public boolean getBook_actived() {
        return this.book_actived;
    }
    public void setBook_actived(boolean book_actived) {
        this.book_actived = book_actived;
    }
   
}
