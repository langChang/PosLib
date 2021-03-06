package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.SystemBook;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SYSTEM_BOOK".
*/
public class SystemBookDao extends AbstractDao<SystemBook, String> {

    public static final String TABLENAME = "SYSTEM_BOOK";

    /**
     * Properties of entity SystemBook.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property System_book_code = new Property(0, String.class, "system_book_code", true, "SYSTEM_BOOK_CODE");
        public final static Property Book_name = new Property(1, String.class, "book_name", false, "BOOK_NAME");
        public final static Property Book_actived = new Property(2, boolean.class, "book_actived", false, "BOOK_ACTIVED");
        public final static Property Book_scope_id = new Property(3, String.class, "book_scope_id", false, "BOOK_SCOPE_ID");
    }


    public SystemBookDao(DaoConfig config) {
        super(config);
    }
    
    public SystemBookDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SYSTEM_BOOK\" (" + //
                "\"SYSTEM_BOOK_CODE\" TEXT PRIMARY KEY NOT NULL ," + // 0: system_book_code
                "\"BOOK_NAME\" TEXT," + // 1: book_name
                "\"BOOK_ACTIVED\" INTEGER NOT NULL ," + // 2: book_actived
                "\"BOOK_SCOPE_ID\" TEXT);"); // 3: book_scope_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SYSTEM_BOOK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SystemBook entity) {
        stmt.clearBindings();
 
        String system_book_code = entity.getSystem_book_code();
        if (system_book_code != null) {
            stmt.bindString(1, system_book_code);
        }
 
        String book_name = entity.getBook_name();
        if (book_name != null) {
            stmt.bindString(2, book_name);
        }
        stmt.bindLong(3, entity.getBook_actived() ? 1L: 0L);
 
        String book_scope_id = entity.getBook_scope_id();
        if (book_scope_id != null) {
            stmt.bindString(4, book_scope_id);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SystemBook entity) {
        stmt.clearBindings();
 
        String system_book_code = entity.getSystem_book_code();
        if (system_book_code != null) {
            stmt.bindString(1, system_book_code);
        }
 
        String book_name = entity.getBook_name();
        if (book_name != null) {
            stmt.bindString(2, book_name);
        }
        stmt.bindLong(3, entity.getBook_actived() ? 1L: 0L);
 
        String book_scope_id = entity.getBook_scope_id();
        if (book_scope_id != null) {
            stmt.bindString(4, book_scope_id);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public SystemBook readEntity(Cursor cursor, int offset) {
        SystemBook entity = new SystemBook( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // system_book_code
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // book_name
            cursor.getShort(offset + 2) != 0, // book_actived
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // book_scope_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SystemBook entity, int offset) {
        entity.setSystem_book_code(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBook_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBook_actived(cursor.getShort(offset + 2) != 0);
        entity.setBook_scope_id(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final String updateKeyAfterInsert(SystemBook entity, long rowId) {
        return entity.getSystem_book_code();
    }
    
    @Override
    public String getKey(SystemBook entity) {
        if(entity != null) {
            return entity.getSystem_book_code();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SystemBook entity) {
        return entity.getSystem_book_code() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
