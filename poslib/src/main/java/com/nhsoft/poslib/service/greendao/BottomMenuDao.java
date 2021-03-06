package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.BottomMenu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOTTOM_MENU".
*/
public class BottomMenuDao extends AbstractDao<BottomMenu, Long> {

    public static final String TABLENAME = "BOTTOM_MENU";

    /**
     * Properties of entity BottomMenu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Menu_id = new Property(0, Long.class, "menu_id", true, "_id");
        public final static Property Menu_type = new Property(1, String.class, "menu_type", false, "MENU_TYPE");
        public final static Property IsPayStyle = new Property(2, boolean.class, "isPayStyle", false, "IS_PAY_STYLE");
        public final static Property Menu_text = new Property(3, String.class, "menu_text", false, "MENU_TEXT");
    }


    public BottomMenuDao(DaoConfig config) {
        super(config);
    }
    
    public BottomMenuDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOTTOM_MENU\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: menu_id
                "\"MENU_TYPE\" TEXT," + // 1: menu_type
                "\"IS_PAY_STYLE\" INTEGER NOT NULL ," + // 2: isPayStyle
                "\"MENU_TEXT\" TEXT);"); // 3: menu_text
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOTTOM_MENU\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BottomMenu entity) {
        stmt.clearBindings();
 
        Long menu_id = entity.getMenu_id();
        if (menu_id != null) {
            stmt.bindLong(1, menu_id);
        }
 
        String menu_type = entity.getMenu_type();
        if (menu_type != null) {
            stmt.bindString(2, menu_type);
        }
        stmt.bindLong(3, entity.getIsPayStyle() ? 1L: 0L);
 
        String menu_text = entity.getMenu_text();
        if (menu_text != null) {
            stmt.bindString(4, menu_text);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BottomMenu entity) {
        stmt.clearBindings();
 
        Long menu_id = entity.getMenu_id();
        if (menu_id != null) {
            stmt.bindLong(1, menu_id);
        }
 
        String menu_type = entity.getMenu_type();
        if (menu_type != null) {
            stmt.bindString(2, menu_type);
        }
        stmt.bindLong(3, entity.getIsPayStyle() ? 1L: 0L);
 
        String menu_text = entity.getMenu_text();
        if (menu_text != null) {
            stmt.bindString(4, menu_text);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BottomMenu readEntity(Cursor cursor, int offset) {
        BottomMenu entity = new BottomMenu( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // menu_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // menu_type
            cursor.getShort(offset + 2) != 0, // isPayStyle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // menu_text
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BottomMenu entity, int offset) {
        entity.setMenu_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMenu_type(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsPayStyle(cursor.getShort(offset + 2) != 0);
        entity.setMenu_text(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BottomMenu entity, long rowId) {
        entity.setMenu_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BottomMenu entity) {
        if(entity != null) {
            return entity.getMenu_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BottomMenu entity) {
        return entity.getMenu_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
