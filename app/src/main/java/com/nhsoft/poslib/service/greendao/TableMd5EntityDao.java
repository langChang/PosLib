package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.TableMd5Entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TABLE_MD5_ENTITY".
*/
public class TableMd5EntityDao extends AbstractDao<TableMd5Entity, Void> {

    public static final String TABLENAME = "TABLE_MD5_ENTITY";

    /**
     * Properties of entity TableMd5Entity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StrKey = new Property(0, String.class, "strKey", false, "STR_KEY");
        public final static Property StrValue = new Property(1, String.class, "strValue", false, "STR_VALUE");
    }


    public TableMd5EntityDao(DaoConfig config) {
        super(config);
    }
    
    public TableMd5EntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TABLE_MD5_ENTITY\" (" + //
                "\"STR_KEY\" TEXT," + // 0: strKey
                "\"STR_VALUE\" TEXT);"); // 1: strValue
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TABLE_MD5_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TableMd5Entity entity) {
        stmt.clearBindings();
 
        String strKey = entity.getStrKey();
        if (strKey != null) {
            stmt.bindString(1, strKey);
        }
 
        String strValue = entity.getStrValue();
        if (strValue != null) {
            stmt.bindString(2, strValue);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TableMd5Entity entity) {
        stmt.clearBindings();
 
        String strKey = entity.getStrKey();
        if (strKey != null) {
            stmt.bindString(1, strKey);
        }
 
        String strValue = entity.getStrValue();
        if (strValue != null) {
            stmt.bindString(2, strValue);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public TableMd5Entity readEntity(Cursor cursor, int offset) {
        TableMd5Entity entity = new TableMd5Entity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // strKey
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // strValue
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TableMd5Entity entity, int offset) {
        entity.setStrKey(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStrValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(TableMd5Entity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(TableMd5Entity entity) {
        return null;
    }

    @Override
    public boolean hasKey(TableMd5Entity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}