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

import com.nhsoft.poslib.entity.PosItemKit;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "POS_ITEM_KIT".
*/
public class PosItemKitDao extends AbstractDao<PosItemKit, Long> {

    public static final String TABLENAME = "POS_ITEM_KIT";

    /**
     * Properties of entity PosItemKit.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Item_num = new Property(1, Long.class, "item_num", false, "ITEM_NUM");
        public final static Property Kit_item_num = new Property(2, long.class, "kit_item_num", false, "KIT_ITEM_NUM");
        public final static Property Pos_item_amount_un_fixed = new Property(3, boolean.class, "pos_item_amount_un_fixed", false, "POS_ITEM_AMOUNT_UN_FIXED");
        public final static Property Pos_item_kit_amount = new Property(4, float.class, "pos_item_kit_amount", false, "POS_ITEM_KIT_AMOUNT");
    }

    private Query<PosItemKit> posItem_Pos_item_kit_listQuery;

    public PosItemKitDao(DaoConfig config) {
        super(config);
    }
    
    public PosItemKitDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"POS_ITEM_KIT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ITEM_NUM\" INTEGER," + // 1: item_num
                "\"KIT_ITEM_NUM\" INTEGER NOT NULL ," + // 2: kit_item_num
                "\"POS_ITEM_AMOUNT_UN_FIXED\" INTEGER NOT NULL ," + // 3: pos_item_amount_un_fixed
                "\"POS_ITEM_KIT_AMOUNT\" REAL NOT NULL );"); // 4: pos_item_kit_amount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"POS_ITEM_KIT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PosItemKit entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long item_num = entity.getItem_num();
        if (item_num != null) {
            stmt.bindLong(2, item_num);
        }
        stmt.bindLong(3, entity.getKit_item_num());
        stmt.bindLong(4, entity.getPos_item_amount_un_fixed() ? 1L: 0L);
        stmt.bindDouble(5, entity.getPos_item_kit_amount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PosItemKit entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long item_num = entity.getItem_num();
        if (item_num != null) {
            stmt.bindLong(2, item_num);
        }
        stmt.bindLong(3, entity.getKit_item_num());
        stmt.bindLong(4, entity.getPos_item_amount_un_fixed() ? 1L: 0L);
        stmt.bindDouble(5, entity.getPos_item_kit_amount());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PosItemKit readEntity(Cursor cursor, int offset) {
        PosItemKit entity = new PosItemKit( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // item_num
            cursor.getLong(offset + 2), // kit_item_num
            cursor.getShort(offset + 3) != 0, // pos_item_amount_un_fixed
            cursor.getFloat(offset + 4) // pos_item_kit_amount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PosItemKit entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setItem_num(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setKit_item_num(cursor.getLong(offset + 2));
        entity.setPos_item_amount_un_fixed(cursor.getShort(offset + 3) != 0);
        entity.setPos_item_kit_amount(cursor.getFloat(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PosItemKit entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PosItemKit entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PosItemKit entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "pos_item_kit_list" to-many relationship of PosItem. */
    public List<PosItemKit> _queryPosItem_Pos_item_kit_list(Long item_num) {
        synchronized (this) {
            if (posItem_Pos_item_kit_listQuery == null) {
                QueryBuilder<PosItemKit> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Item_num.eq(null));
                posItem_Pos_item_kit_listQuery = queryBuilder.build();
            }
        }
        Query<PosItemKit> query = posItem_Pos_item_kit_listQuery.forCurrentThread();
        query.setParameter(0, item_num);
        return query.list();
    }

}
