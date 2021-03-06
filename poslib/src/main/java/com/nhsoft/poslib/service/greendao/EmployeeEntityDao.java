package com.nhsoft.poslib.service.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nhsoft.poslib.entity.EmployeeEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EMPLOYEE_ENTITY".
*/
public class EmployeeEntityDao extends AbstractDao<EmployeeEntity, Void> {

    public static final String TABLENAME = "EMPLOYEE_ENTITY";

    /**
     * Properties of entity EmployeeEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Branch_num = new Property(0, int.class, "branch_num", false, "BRANCH_NUM");
        public final static Property Employee_num = new Property(1, int.class, "employee_num", false, "EMPLOYEE_NUM");
        public final static Property Employee_code = new Property(2, String.class, "employee_code", false, "EMPLOYEE_CODE");
        public final static Property Employee_name = new Property(3, String.class, "employee_name", false, "EMPLOYEE_NAME");
        public final static Property Employee_kind = new Property(4, String.class, "employee_kind", false, "EMPLOYEE_KIND");
        public final static Property Employee_actived = new Property(5, boolean.class, "employee_actived", false, "EMPLOYEE_ACTIVED");
    }


    public EmployeeEntityDao(DaoConfig config) {
        super(config);
    }
    
    public EmployeeEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EMPLOYEE_ENTITY\" (" + //
                "\"BRANCH_NUM\" INTEGER NOT NULL ," + // 0: branch_num
                "\"EMPLOYEE_NUM\" INTEGER NOT NULL ," + // 1: employee_num
                "\"EMPLOYEE_CODE\" TEXT," + // 2: employee_code
                "\"EMPLOYEE_NAME\" TEXT," + // 3: employee_name
                "\"EMPLOYEE_KIND\" TEXT," + // 4: employee_kind
                "\"EMPLOYEE_ACTIVED\" INTEGER NOT NULL );"); // 5: employee_actived
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EMPLOYEE_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EmployeeEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBranch_num());
        stmt.bindLong(2, entity.getEmployee_num());
 
        String employee_code = entity.getEmployee_code();
        if (employee_code != null) {
            stmt.bindString(3, employee_code);
        }
 
        String employee_name = entity.getEmployee_name();
        if (employee_name != null) {
            stmt.bindString(4, employee_name);
        }
 
        String employee_kind = entity.getEmployee_kind();
        if (employee_kind != null) {
            stmt.bindString(5, employee_kind);
        }
        stmt.bindLong(6, entity.getEmployee_actived() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EmployeeEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBranch_num());
        stmt.bindLong(2, entity.getEmployee_num());
 
        String employee_code = entity.getEmployee_code();
        if (employee_code != null) {
            stmt.bindString(3, employee_code);
        }
 
        String employee_name = entity.getEmployee_name();
        if (employee_name != null) {
            stmt.bindString(4, employee_name);
        }
 
        String employee_kind = entity.getEmployee_kind();
        if (employee_kind != null) {
            stmt.bindString(5, employee_kind);
        }
        stmt.bindLong(6, entity.getEmployee_actived() ? 1L: 0L);
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public EmployeeEntity readEntity(Cursor cursor, int offset) {
        EmployeeEntity entity = new EmployeeEntity( //
            cursor.getInt(offset + 0), // branch_num
            cursor.getInt(offset + 1), // employee_num
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // employee_code
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // employee_name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // employee_kind
            cursor.getShort(offset + 5) != 0 // employee_actived
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EmployeeEntity entity, int offset) {
        entity.setBranch_num(cursor.getInt(offset + 0));
        entity.setEmployee_num(cursor.getInt(offset + 1));
        entity.setEmployee_code(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmployee_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmployee_kind(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmployee_actived(cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Void updateKeyAfterInsert(EmployeeEntity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(EmployeeEntity entity) {
        return null;
    }

    @Override
    public boolean hasKey(EmployeeEntity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
