package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.ChangeGoodsMenu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHANGE_GOODS_MENU".
*/
public class ChangeGoodsMenuDao extends AbstractDao<ChangeGoodsMenu, Long> {

    public static final String TABLENAME = "CHANGE_GOODS_MENU";

    /**
     * Properties of entity ChangeGoodsMenu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Menu_id = new Property(0, Long.class, "menu_id", true, "_id");
        public final static Property Menu_text = new Property(1, String.class, "menu_text", false, "MENU_TEXT");
    }


    public ChangeGoodsMenuDao(DaoConfig config) {
        super(config);
    }
    
    public ChangeGoodsMenuDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHANGE_GOODS_MENU\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: menu_id
                "\"MENU_TEXT\" TEXT);"); // 1: menu_text
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHANGE_GOODS_MENU\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChangeGoodsMenu entity) {
        stmt.clearBindings();
 
        Long menu_id = entity.getMenu_id();
        if (menu_id != null) {
            stmt.bindLong(1, menu_id);
        }
 
        String menu_text = entity.getMenu_text();
        if (menu_text != null) {
            stmt.bindString(2, menu_text);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChangeGoodsMenu entity) {
        stmt.clearBindings();
 
        Long menu_id = entity.getMenu_id();
        if (menu_id != null) {
            stmt.bindLong(1, menu_id);
        }
 
        String menu_text = entity.getMenu_text();
        if (menu_text != null) {
            stmt.bindString(2, menu_text);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChangeGoodsMenu readEntity(Cursor cursor, int offset) {
        ChangeGoodsMenu entity = new ChangeGoodsMenu( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // menu_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // menu_text
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChangeGoodsMenu entity, int offset) {
        entity.setMenu_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMenu_text(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChangeGoodsMenu entity, long rowId) {
        entity.setMenu_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChangeGoodsMenu entity) {
        if(entity != null) {
            return entity.getMenu_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChangeGoodsMenu entity) {
        return entity.getMenu_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}