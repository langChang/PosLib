package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.SystemPrint;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SYSTEM_PRINT".
*/
public class SystemPrintDao extends AbstractDao<SystemPrint, String> {

    public static final String TABLENAME = "SYSTEM_PRINT";

    /**
     * Properties of entity SystemPrint.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SystemPrintID = new Property(0, String.class, "SystemPrintID", true, "SYSTEM_PRINT_ID");
        public final static Property Logo = new Property(1, int.class, "logo", false, "LOGO");
        public final static Property LogoName = new Property(2, String.class, "logoName", false, "LOGO_NAME");
        public final static Property TitleSlogan = new Property(3, String.class, "titleSlogan", false, "TITLE_SLOGAN");
        public final static Property SelfSlogan = new Property(4, int.class, "selfSlogan", false, "SELF_SLOGAN");
        public final static Property FootSlogan = new Property(5, String.class, "footSlogan", false, "FOOT_SLOGAN");
        public final static Property ItemSelf = new Property(6, String.class, "itemSelf", false, "ITEM_SELF");
        public final static Property IsSlogan = new Property(7, boolean.class, "isSlogan", false, "IS_SLOGAN");
        public final static Property Isfoot = new Property(8, boolean.class, "isfoot", false, "ISFOOT");
        public final static Property IsSelf = new Property(9, boolean.class, "isSelf", false, "IS_SELF");
    }


    public SystemPrintDao(DaoConfig config) {
        super(config);
    }
    
    public SystemPrintDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SYSTEM_PRINT\" (" + //
                "\"SYSTEM_PRINT_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: SystemPrintID
                "\"LOGO\" INTEGER NOT NULL ," + // 1: logo
                "\"LOGO_NAME\" TEXT," + // 2: logoName
                "\"TITLE_SLOGAN\" TEXT," + // 3: titleSlogan
                "\"SELF_SLOGAN\" INTEGER NOT NULL ," + // 4: selfSlogan
                "\"FOOT_SLOGAN\" TEXT," + // 5: footSlogan
                "\"ITEM_SELF\" TEXT," + // 6: itemSelf
                "\"IS_SLOGAN\" INTEGER NOT NULL ," + // 7: isSlogan
                "\"ISFOOT\" INTEGER NOT NULL ," + // 8: isfoot
                "\"IS_SELF\" INTEGER NOT NULL );"); // 9: isSelf
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SYSTEM_PRINT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SystemPrint entity) {
        stmt.clearBindings();
 
        String SystemPrintID = entity.getSystemPrintID();
        if (SystemPrintID != null) {
            stmt.bindString(1, SystemPrintID);
        }
        stmt.bindLong(2, entity.getLogo());
 
        String logoName = entity.getLogoName();
        if (logoName != null) {
            stmt.bindString(3, logoName);
        }
 
        String titleSlogan = entity.getTitleSlogan();
        if (titleSlogan != null) {
            stmt.bindString(4, titleSlogan);
        }
        stmt.bindLong(5, entity.getSelfSlogan());
 
        String footSlogan = entity.getFootSlogan();
        if (footSlogan != null) {
            stmt.bindString(6, footSlogan);
        }
 
        String itemSelf = entity.getItemSelf();
        if (itemSelf != null) {
            stmt.bindString(7, itemSelf);
        }
        stmt.bindLong(8, entity.getIsSlogan() ? 1L: 0L);
        stmt.bindLong(9, entity.getIsfoot() ? 1L: 0L);
        stmt.bindLong(10, entity.getIsSelf() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SystemPrint entity) {
        stmt.clearBindings();
 
        String SystemPrintID = entity.getSystemPrintID();
        if (SystemPrintID != null) {
            stmt.bindString(1, SystemPrintID);
        }
        stmt.bindLong(2, entity.getLogo());
 
        String logoName = entity.getLogoName();
        if (logoName != null) {
            stmt.bindString(3, logoName);
        }
 
        String titleSlogan = entity.getTitleSlogan();
        if (titleSlogan != null) {
            stmt.bindString(4, titleSlogan);
        }
        stmt.bindLong(5, entity.getSelfSlogan());
 
        String footSlogan = entity.getFootSlogan();
        if (footSlogan != null) {
            stmt.bindString(6, footSlogan);
        }
 
        String itemSelf = entity.getItemSelf();
        if (itemSelf != null) {
            stmt.bindString(7, itemSelf);
        }
        stmt.bindLong(8, entity.getIsSlogan() ? 1L: 0L);
        stmt.bindLong(9, entity.getIsfoot() ? 1L: 0L);
        stmt.bindLong(10, entity.getIsSelf() ? 1L: 0L);
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public SystemPrint readEntity(Cursor cursor, int offset) {
        SystemPrint entity = new SystemPrint( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // SystemPrintID
            cursor.getInt(offset + 1), // logo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // logoName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // titleSlogan
            cursor.getInt(offset + 4), // selfSlogan
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // footSlogan
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // itemSelf
            cursor.getShort(offset + 7) != 0, // isSlogan
            cursor.getShort(offset + 8) != 0, // isfoot
            cursor.getShort(offset + 9) != 0 // isSelf
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SystemPrint entity, int offset) {
        entity.setSystemPrintID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setLogo(cursor.getInt(offset + 1));
        entity.setLogoName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitleSlogan(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSelfSlogan(cursor.getInt(offset + 4));
        entity.setFootSlogan(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setItemSelf(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIsSlogan(cursor.getShort(offset + 7) != 0);
        entity.setIsfoot(cursor.getShort(offset + 8) != 0);
        entity.setIsSelf(cursor.getShort(offset + 9) != 0);
     }
    
    @Override
    protected final String updateKeyAfterInsert(SystemPrint entity, long rowId) {
        return entity.getSystemPrintID();
    }
    
    @Override
    public String getKey(SystemPrint entity) {
        if(entity != null) {
            return entity.getSystemPrintID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SystemPrint entity) {
        return entity.getSystemPrintID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
