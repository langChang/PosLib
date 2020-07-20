package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.model.PosItemImg;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.ItemBarDao;
import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.PosItemKitDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/14 12:19 PM
 * 此类用于：
 */
@Entity
public class PosItem implements Cloneable{

    /**
     * item_num : 444400587
     * item_name : 辅助单位测试
     * store_item_pinyin : FZDWCS  速记码
     * item_type : 1
     * item_unit : 斤
     * item_category : 勿动
     * item_min_price : 0.0
     * item_category_code : 0
     * item_department : 123
     * item_sale_message : false
     * item_pos_change_price_flag : true
     * item_del_tag : false
     * item_point_actived : false
     * item_point_value : 0.0
     * item_brand :
     * item_cost_mode : 加权平均法
     * item_valid_period : 0
     * item_cost_price : 1.236
     * item_transfer_price : 1.4832
     * item_assist_unit : 一篮子
     * item_sequence : 6
     * item_eliminative_flag : false
     * item_create_time : 2017-02-07 08:53:42
     * item_assist_rate : 2.5
     * item_status : 0
     * item_discounted : false
     * item_weight_flag : true
     * branch_sale_cease_flag : false
     * branch_max_price : 0.0
     * branch_regular_price : 24.2
     * branch_level2_price : 0.0
     * branch_level3_price : 0.0
     * branch_level4_price : 0.0
     * item_bar_list : [{"item_num":444400587,"item_bar_num":1,"item_bar_code":"22000004","item_bar_rate":1}]
     * pos_item_grade_list : [{"item_grade_num":650,"item_num":444401158,"item_grade_code":"00197","item_grade_barcode":"","item_grade_name":"1","item_grade_discounted":true,"item_grade_pinyin":"1","branch_grade_regular_price":0,"branch_grade_level2_price":0,"branch_grade_level3_price":0,"branch_grade_level4_price":0}]
     * pos_item_kit_list : [{"item_num":444400786,"kit_item_num":444400584,"pos_item_amount_un_fixed":false},{"item_num":444400786,"kit_item_num":444400777,"pos_item_amount_un_fixed":false}]
     */
    
    @Id
    @Property(nameInDb = "ITEM_NUM")
    private Long item_num;
    private String  item_name;
    private String  store_item_pinyin;
    private int     item_type;
    private String  item_unit;
    private String  item_category;
    private float  item_min_price;
    private String  item_category_code;
    private String  item_department;
    private boolean item_sale_message;
    private boolean item_pos_change_price_flag;
    private boolean item_del_tag;
    private boolean item_point_actived;
    private float  item_point_value;
    private String  item_brand;
    private String  item_cost_mode;
    private int     item_valid_period;
    private float  item_cost_price;
    private float  item_transfer_price;
    private String  item_assist_unit;
    private int     item_sequence = 1;
    private boolean item_eliminative_flag;
    private String  item_create_time;
    private String  item_last_edit_time;
    private float  item_assist_rate;
    private int     item_status;
    private boolean item_discounted;
    private boolean item_weight_flag;
    private boolean branch_sale_cease_flag;
    private Boolean item_sale_cease_flag;

    private float            branch_max_price;
    private Float branch_min_price;
    private Float item_max_price;
    private float            branch_regular_price;
    private float            branch_level2_price;
    private float            branch_level3_price;
    private float            branch_level4_price;
    private int              branch_num;
    private float            item_regular_price;
    private float            item_level2_price;
    private float            item_level3_price;
    private float            item_level4_price;
    private Boolean          pos_item_selected;//终端商品设置的时候判断该item是否选中默认false
    private String           pos_images_json;


    @Transient
    private PosItemGrade showPosItemGrade;
    @Transient
    private List<PosItemKit> posItemKits = new ArrayList<>();


    public List<PosItemKit> getPosItemKits() {
        return posItemKits;
    }

    public void setPosItemKits(List<PosItemKit> posItemKits) {
        this.posItemKits = posItemKits;
    }

    public PosItemGrade getShowPosItemGrade() {
        return showPosItemGrade;
    }

    public void setShowPosItemGrade(PosItemGrade showPosItemGrade) {
        this.showPosItemGrade = showPosItemGrade;
    }



    @Transient
    private List<PosItemImg> pos_images;

    @Transient
    private ItemBar itemBar;

    public ItemBar getItemBar() {
        return itemBar;
    }

    public void setItemBar(ItemBar itemBar) {
        this.itemBar = itemBar;
    }


    public String getPos_images_json() {
        return pos_images_json;
    }

    public void setPos_images_json(String pos_images_json){
        this.pos_images_json = pos_images_json;
    }

    public List<PosItemImg> getPos_Images() {
        return pos_images;
    }

    public void setPos_Images(List<PosItemImg> stalls) {
        this.pos_images = stalls;
    }

    @Transient
    private PosItemGrade posItemGrade;
    @Transient float stall_regular_price;//农贸标准价
    @Transient float stall_vip_price;//农贸会员价
    public float getStall_vip_price() {
        return stall_vip_price;
    }

    public void setStall_vip_price(float stall_vip_price) {
        this.stall_vip_price = stall_vip_price;
    }



    public float getStall_regular_price() {
        return stall_regular_price;
    }

    public void setStall_regular_price(float stall_regular_price) {
        this.stall_regular_price = stall_regular_price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Transient
    private String item_barcode;//item_barcode

    public String getItem_barcode() {
        return item_barcode;
    }

    public void setItem_barcode(String item_barcode) {
        this.item_barcode = item_barcode;
    }

    public float getItem_regular_price() {
        return item_regular_price;
    }

    public void setItem_regular_price(float item_regular_price) {
        this.item_regular_price = item_regular_price;
    }

    @ToMany(referencedJoinProperty = "item_num")
    private List<ItemBar>      item_bar_list;
    @ToMany(referencedJoinProperty = "item_num")
    private List<PosItemGrade> pos_item_grade_list;
    @ToMany(referencedJoinProperty = "item_num")
    private List<PosItemKit>   pos_item_kit_list;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 398412949)
    private transient PosItemDao myDao;

    @Generated(hash = 541990426)
    public PosItem(Long item_num, String item_name, String store_item_pinyin, int item_type, String item_unit, String item_category, float item_min_price, String item_category_code, String item_department, boolean item_sale_message, boolean item_pos_change_price_flag, boolean item_del_tag, boolean item_point_actived,
            float item_point_value, String item_brand, String item_cost_mode, int item_valid_period, float item_cost_price, float item_transfer_price, String item_assist_unit, int item_sequence, boolean item_eliminative_flag, String item_create_time, String item_last_edit_time, float item_assist_rate, int item_status,
            boolean item_discounted, boolean item_weight_flag, boolean branch_sale_cease_flag, Boolean item_sale_cease_flag, float branch_max_price, Float branch_min_price, Float item_max_price, float branch_regular_price, float branch_level2_price, float branch_level3_price, float branch_level4_price, int branch_num,
            float item_regular_price, float item_level2_price, float item_level3_price, float item_level4_price, Boolean pos_item_selected, String pos_images_json) {
        this.item_num = item_num;
        this.item_name = item_name;
        this.store_item_pinyin = store_item_pinyin;
        this.item_type = item_type;
        this.item_unit = item_unit;
        this.item_category = item_category;
        this.item_min_price = item_min_price;
        this.item_category_code = item_category_code;
        this.item_department = item_department;
        this.item_sale_message = item_sale_message;
        this.item_pos_change_price_flag = item_pos_change_price_flag;
        this.item_del_tag = item_del_tag;
        this.item_point_actived = item_point_actived;
        this.item_point_value = item_point_value;
        this.item_brand = item_brand;
        this.item_cost_mode = item_cost_mode;
        this.item_valid_period = item_valid_period;
        this.item_cost_price = item_cost_price;
        this.item_transfer_price = item_transfer_price;
        this.item_assist_unit = item_assist_unit;
        this.item_sequence = item_sequence;
        this.item_eliminative_flag = item_eliminative_flag;
        this.item_create_time = item_create_time;
        this.item_last_edit_time = item_last_edit_time;
        this.item_assist_rate = item_assist_rate;
        this.item_status = item_status;
        this.item_discounted = item_discounted;
        this.item_weight_flag = item_weight_flag;
        this.branch_sale_cease_flag = branch_sale_cease_flag;
        this.item_sale_cease_flag = item_sale_cease_flag;
        this.branch_max_price = branch_max_price;
        this.branch_min_price = branch_min_price;
        this.item_max_price = item_max_price;
        this.branch_regular_price = branch_regular_price;
        this.branch_level2_price = branch_level2_price;
        this.branch_level3_price = branch_level3_price;
        this.branch_level4_price = branch_level4_price;
        this.branch_num = branch_num;
        this.item_regular_price = item_regular_price;
        this.item_level2_price = item_level2_price;
        this.item_level3_price = item_level3_price;
        this.item_level4_price = item_level4_price;
        this.pos_item_selected = pos_item_selected;
        this.pos_images_json = pos_images_json;
    }

    @Generated(hash = 181562328)
    public PosItem() {
    }

    
    
    public Long getItem_num() {
        return this.item_num;
    }
    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }
    public String getItem_name() {
        return this.item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public String getStore_item_pinyin() {
        return this.store_item_pinyin;
    }
    public void setStore_item_pinyin(String store_item_pinyin) {
        this.store_item_pinyin = store_item_pinyin;
    }
    public int getItem_type() {
        return this.item_type;
    }
    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
    public String getItem_unit() {
        return this.item_unit;
    }
    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }
    public String getItem_category() {
        return this.item_category;
    }
    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }
    public float getItem_min_price() {
        return this.item_min_price;
    }
    public void setItem_min_price(float item_min_price) {
        this.item_min_price = item_min_price;
    }
    public String getItem_category_code() {
        return this.item_category_code;
    }
    public void setItem_category_code(String item_category_code) {
        this.item_category_code = item_category_code;
    }
    public String getItem_department() {
        return this.item_department;
    }
    public void setItem_department(String item_department) {
        this.item_department = item_department;
    }
    public boolean getItem_sale_message() {
        return this.item_sale_message;
    }
    public void setItem_sale_message(boolean item_sale_message) {
        this.item_sale_message = item_sale_message;
    }
    public boolean getItem_pos_change_price_flag() {
        return this.item_pos_change_price_flag;
    }
    public void setItem_pos_change_price_flag(boolean item_pos_change_price_flag) {
        this.item_pos_change_price_flag = item_pos_change_price_flag;
    }
    public boolean getItem_del_tag() {
        return this.item_del_tag;
    }
    public void setItem_del_tag(boolean item_del_tag) {
        this.item_del_tag = item_del_tag;
    }
    public boolean getItem_point_actived() {
        return this.item_point_actived;
    }
    public void setItem_point_actived(boolean item_point_actived) {
        this.item_point_actived = item_point_actived;
    }
    public float getItem_point_value() {
        return this.item_point_value;
    }
    public void setItem_point_value(float item_point_value) {
        this.item_point_value = item_point_value;
    }
    public String getItem_brand() {
        return this.item_brand;
    }
    public void setItem_brand(String item_brand) {
        this.item_brand = item_brand;
    }
    public String getItem_cost_mode() {
        return this.item_cost_mode;
    }
    public void setItem_cost_mode(String item_cost_mode) {
        this.item_cost_mode = item_cost_mode;
    }
    public int getItem_valid_period() {
        return this.item_valid_period;
    }
    public void setItem_valid_period(int item_valid_period) {
        this.item_valid_period = item_valid_period;
    }
    public float getItem_cost_price() {
        return this.item_cost_price;
    }
    public void setItem_cost_price(float item_cost_price) {
        this.item_cost_price = item_cost_price;
    }
    public float getItem_transfer_price() {
        return this.item_transfer_price;
    }
    public void setItem_transfer_price(float item_transfer_price) {
        this.item_transfer_price = item_transfer_price;
    }
    public String getItem_assist_unit() {
        return this.item_assist_unit;
    }
    public void setItem_assist_unit(String item_assist_unit) {
        this.item_assist_unit = item_assist_unit;
    }
    public int getItem_sequence() {
        return this.item_sequence;
    }
    public void setItem_sequence(int item_sequence) {
        this.item_sequence = item_sequence;
    }
    public boolean getItem_eliminative_flag() {
        return this.item_eliminative_flag;
    }
    public void setItem_eliminative_flag(boolean item_eliminative_flag) {
        this.item_eliminative_flag = item_eliminative_flag;
    }
    public String getItem_create_time() {
        return this.item_create_time;
    }
    public void setItem_create_time(String item_create_time) {
        this.item_create_time = item_create_time;
    }
    public String getItem_last_edit_time() {
        return this.item_last_edit_time;
    }
    public void setItem_last_edit_time(String item_last_edit_time) {
        this.item_last_edit_time = item_last_edit_time;
    }
    public float getItem_assist_rate() {
        return this.item_assist_rate;
    }
    public void setItem_assist_rate(float item_assist_rate) {
        this.item_assist_rate = item_assist_rate;
    }
    public int getItem_status() {
        return this.item_status;
    }
    public void setItem_status(int item_status) {
        this.item_status = item_status;
    }
    public boolean getItem_discounted() {
        return this.item_discounted;
    }
    public void setItem_discounted(boolean item_discounted) {
        this.item_discounted = item_discounted;
    }
    public boolean getItem_weight_flag() {
        return this.item_weight_flag;
    }
    public void setItem_weight_flag(boolean item_weight_flag) {
        this.item_weight_flag = item_weight_flag;
    }
    public boolean getBranch_sale_cease_flag() {
        return this.branch_sale_cease_flag;
    }
    public void setBranch_sale_cease_flag(boolean branch_sale_cease_flag) {
        this.branch_sale_cease_flag = branch_sale_cease_flag;
    }
    public float getBranch_max_price() {
        return this.branch_max_price;
    }
    public void setBranch_max_price(float branch_max_price) {
        this.branch_max_price = branch_max_price;
    }
    public float getBranch_regular_price() {
        return this.branch_regular_price;
    }
    public void setBranch_regular_price(float branch_regular_price) {
        this.branch_regular_price = branch_regular_price;
    }
    public float getBranch_level2_price() {
        return this.branch_level2_price;
    }
    public void setBranch_level2_price(float branch_level2_price) {
        this.branch_level2_price = branch_level2_price;
    }
    public float getBranch_level3_price() {
        return this.branch_level3_price;
    }
    public void setBranch_level3_price(float branch_level3_price) {
        this.branch_level3_price = branch_level3_price;
    }
    public float getBranch_level4_price() {
        return this.branch_level4_price;
    }
    public void setBranch_level4_price(float branch_level4_price) {
        this.branch_level4_price = branch_level4_price;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1090584564)
    public List<ItemBar> getItem_bar_list() {
        if (item_bar_list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemBarDao targetDao = daoSession.getItemBarDao();
            List<ItemBar> item_bar_listNew = targetDao._queryPosItem_Item_bar_list(item_num);
            synchronized (this) {
                if (item_bar_list == null) {
                    item_bar_list = item_bar_listNew;
                }
            }
        }
        return item_bar_list;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 221103845)
    public synchronized void resetItem_bar_list() {
        item_bar_list = null;
    }

    public void setItem_bar_list(List<ItemBar>item_bars){
        this.item_bar_list=item_bars;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 826999103)
    public List<PosItemGrade> getPos_item_grade_list() {
        if (pos_item_grade_list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PosItemGradeDao targetDao = daoSession.getPosItemGradeDao();
            List<PosItemGrade> pos_item_grade_listNew = targetDao._queryPosItem_Pos_item_grade_list(item_num);
            synchronized (this) {
                if (pos_item_grade_list == null) {
                    pos_item_grade_list = pos_item_grade_listNew;
                }
            }
        }
        return pos_item_grade_list;
    }


    public void setPos_item_grade_list(List<PosItemGrade> pos_item_grade_list){
        this.pos_item_grade_list = pos_item_grade_list;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1359919193)
    public synchronized void resetPos_item_grade_list() {
        pos_item_grade_list = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1539987090)
    public List<PosItemKit> getPos_item_kit_list() {
        if (pos_item_kit_list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PosItemKitDao targetDao = daoSession.getPosItemKitDao();
            List<PosItemKit> pos_item_kit_listNew = targetDao._queryPosItem_Pos_item_kit_list(item_num);
            synchronized (this) {
                if (pos_item_kit_list == null) {
                    pos_item_kit_list = pos_item_kit_listNew;
                }
            }
        }
        return pos_item_kit_list;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2130376343)
    public synchronized void resetPos_item_kit_list() {
        pos_item_kit_list = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    public float getItem_level2_price() {
        return this.item_level2_price;
    }

    public void setItem_level2_price(float item_level2_price) {
        this.item_level2_price = item_level2_price;
    }

    public float getItem_level3_price() {
        return this.item_level3_price;
    }

    public void setItem_level3_price(float item_level3_price) {
        this.item_level3_price = item_level3_price;
    }

    public float getItem_level4_price() {
        return this.item_level4_price;
    }

    public void setItem_level4_price(float item_level4_price) {
        this.item_level4_price = item_level4_price;
    }

    public PosItemGrade getPosItemGrade() {
        return posItemGrade;
    }

    public void setPosItemGrade(PosItemGrade posItemGrade) {
        this.posItemGrade = posItemGrade;
    }


    public Boolean getPos_item_selected() {
        return this.pos_item_selected==null?false:this.pos_item_selected;
    }

    public void setPos_item_selected(Boolean pos_item_selected) {
        this.pos_item_selected = pos_item_selected;
    }

    public Float getBranch_min_price() {
        return this.branch_min_price;
    }

    public void setBranch_min_price(Float branch_min_price) {
        this.branch_min_price = branch_min_price;
    }

    public Float getItem_max_price() {
        return this.item_max_price;
    }

    public void setItem_max_price(Float item_max_price) {
        this.item_max_price = item_max_price;
    }

    public Boolean getItem_sale_cease_flag() {
        return this.item_sale_cease_flag == null ? false : this.item_sale_cease_flag;
    }

    public void setItem_sale_cease_flag(Boolean item_sale_cease_flag) {
        this.item_sale_cease_flag = item_sale_cease_flag;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 452966027)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPosItemDao() : null;
    }




}
