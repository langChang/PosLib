package com.nhsoft.poslib.service.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.nhsoft.poslib.entity.nongmao.StallPromotionDetail;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STALL_PROMOTION_DETAIL".
*/
public class StallPromotionDetailDao extends AbstractDao<StallPromotionDetail, Long> {

    public static final String TABLENAME = "STALL_PROMOTION_DETAIL";

    /**
     * Properties of entity StallPromotionDetail.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Policy_promotion_no = new Property(1, String.class, "policy_promotion_no", false, "POLICY_PROMOTION_NO");
        public final static Property Item_grade_num = new Property(2, int.class, "item_grade_num", false, "ITEM_GRADE_NUM");
        public final static Property Item_num = new Property(3, int.class, "item_num", false, "ITEM_NUM");
        public final static Property Policy_promotion_detail_bill_limit = new Property(4, float.class, "policy_promotion_detail_bill_limit", false, "POLICY_PROMOTION_DETAIL_BILL_LIMIT");
        public final static Property Policy_promotion_detail_memo = new Property(5, String.class, "policy_promotion_detail_memo", false, "POLICY_PROMOTION_DETAIL_MEMO");
        public final static Property Policy_promotion_detail_num = new Property(6, int.class, "policy_promotion_detail_num", false, "POLICY_PROMOTION_DETAIL_NUM");
        public final static Property Policy_promotion_detail_rate = new Property(7, float.class, "policy_promotion_detail_rate", false, "POLICY_PROMOTION_DETAIL_RATE");
        public final static Property Policy_promotion_detail_special_price = new Property(8, float.class, "policy_promotion_detail_special_price", false, "POLICY_PROMOTION_DETAIL_SPECIAL_PRICE");
        public final static Property Policy_promotion_detail_std_price = new Property(9, float.class, "policy_promotion_detail_std_price", false, "POLICY_PROMOTION_DETAIL_STD_PRICE");
    }

    private Query<StallPromotionDetail> stallPromotion_DetailsQuery;

    public StallPromotionDetailDao(DaoConfig config) {
        super(config);
    }
    
    public StallPromotionDetailDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STALL_PROMOTION_DETAIL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"POLICY_PROMOTION_NO\" TEXT," + // 1: policy_promotion_no
                "\"ITEM_GRADE_NUM\" INTEGER NOT NULL ," + // 2: item_grade_num
                "\"ITEM_NUM\" INTEGER NOT NULL ," + // 3: item_num
                "\"POLICY_PROMOTION_DETAIL_BILL_LIMIT\" REAL NOT NULL ," + // 4: policy_promotion_detail_bill_limit
                "\"POLICY_PROMOTION_DETAIL_MEMO\" TEXT," + // 5: policy_promotion_detail_memo
                "\"POLICY_PROMOTION_DETAIL_NUM\" INTEGER NOT NULL ," + // 6: policy_promotion_detail_num
                "\"POLICY_PROMOTION_DETAIL_RATE\" REAL NOT NULL ," + // 7: policy_promotion_detail_rate
                "\"POLICY_PROMOTION_DETAIL_SPECIAL_PRICE\" REAL NOT NULL ," + // 8: policy_promotion_detail_special_price
                "\"POLICY_PROMOTION_DETAIL_STD_PRICE\" REAL NOT NULL );"); // 9: policy_promotion_detail_std_price
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STALL_PROMOTION_DETAIL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, StallPromotionDetail entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String policy_promotion_no = entity.getPolicy_promotion_no();
        if (policy_promotion_no != null) {
            stmt.bindString(2, policy_promotion_no);
        }
        stmt.bindLong(3, entity.getItem_grade_num());
        stmt.bindLong(4, entity.getItem_num());
        stmt.bindDouble(5, entity.getPolicy_promotion_detail_bill_limit());
 
        String policy_promotion_detail_memo = entity.getPolicy_promotion_detail_memo();
        if (policy_promotion_detail_memo != null) {
            stmt.bindString(6, policy_promotion_detail_memo);
        }
        stmt.bindLong(7, entity.getPolicy_promotion_detail_num());
        stmt.bindDouble(8, entity.getPolicy_promotion_detail_rate());
        stmt.bindDouble(9, entity.getPolicy_promotion_detail_special_price());
        stmt.bindDouble(10, entity.getPolicy_promotion_detail_std_price());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, StallPromotionDetail entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String policy_promotion_no = entity.getPolicy_promotion_no();
        if (policy_promotion_no != null) {
            stmt.bindString(2, policy_promotion_no);
        }
        stmt.bindLong(3, entity.getItem_grade_num());
        stmt.bindLong(4, entity.getItem_num());
        stmt.bindDouble(5, entity.getPolicy_promotion_detail_bill_limit());
 
        String policy_promotion_detail_memo = entity.getPolicy_promotion_detail_memo();
        if (policy_promotion_detail_memo != null) {
            stmt.bindString(6, policy_promotion_detail_memo);
        }
        stmt.bindLong(7, entity.getPolicy_promotion_detail_num());
        stmt.bindDouble(8, entity.getPolicy_promotion_detail_rate());
        stmt.bindDouble(9, entity.getPolicy_promotion_detail_special_price());
        stmt.bindDouble(10, entity.getPolicy_promotion_detail_std_price());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public StallPromotionDetail readEntity(Cursor cursor, int offset) {
        StallPromotionDetail entity = new StallPromotionDetail( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // policy_promotion_no
            cursor.getInt(offset + 2), // item_grade_num
            cursor.getInt(offset + 3), // item_num
            cursor.getFloat(offset + 4), // policy_promotion_detail_bill_limit
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // policy_promotion_detail_memo
            cursor.getInt(offset + 6), // policy_promotion_detail_num
            cursor.getFloat(offset + 7), // policy_promotion_detail_rate
            cursor.getFloat(offset + 8), // policy_promotion_detail_special_price
            cursor.getFloat(offset + 9) // policy_promotion_detail_std_price
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, StallPromotionDetail entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPolicy_promotion_no(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setItem_grade_num(cursor.getInt(offset + 2));
        entity.setItem_num(cursor.getInt(offset + 3));
        entity.setPolicy_promotion_detail_bill_limit(cursor.getFloat(offset + 4));
        entity.setPolicy_promotion_detail_memo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPolicy_promotion_detail_num(cursor.getInt(offset + 6));
        entity.setPolicy_promotion_detail_rate(cursor.getFloat(offset + 7));
        entity.setPolicy_promotion_detail_special_price(cursor.getFloat(offset + 8));
        entity.setPolicy_promotion_detail_std_price(cursor.getFloat(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(StallPromotionDetail entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(StallPromotionDetail entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(StallPromotionDetail entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "details" to-many relationship of StallPromotion. */
    public List<StallPromotionDetail> _queryStallPromotion_Details(String policy_promotion_no) {
        synchronized (this) {
            if (stallPromotion_DetailsQuery == null) {
                QueryBuilder<StallPromotionDetail> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Policy_promotion_no.eq(null));
                stallPromotion_DetailsQuery = queryBuilder.build();
            }
        }
        Query<StallPromotionDetail> query = stallPromotion_DetailsQuery.forCurrentThread();
        query.setParameter(0, policy_promotion_no);
        return query.list();
    }

}