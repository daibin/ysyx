package com.ysyx.androidvlc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.ysyx.androidvlc.CameraInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CAMERA_INFO.
*/
public class CameraInfoDao extends AbstractDao<CameraInfo, Long> {

    public static final String TABLENAME = "CAMERA_INFO";

    /**
     * Properties of entity CameraInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NAME = new Property(1, String.class, "NAME", false, "NAME");
        public final static Property STD_ID = new Property(2, String.class, "STD_ID", false, "STD__ID");
        public final static Property GPS_X = new Property(3, String.class, "GPS_X", false, "GPS__X");
        public final static Property GPS_Y = new Property(4, String.class, "GPS_Y", false, "GPS__Y");
        public final static Property GPS_Z = new Property(5, String.class, "GPS_Z", false, "GPS__Z");
    };


    public CameraInfoDao(DaoConfig config) {
        super(config);
    }
    
    public CameraInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CAMERA_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT NOT NULL ," + // 1: NAME
                "'STD__ID' TEXT NOT NULL ," + // 2: STD_ID
                "'GPS__X' TEXT," + // 3: GPS_X
                "'GPS__Y' TEXT," + // 4: GPS_Y
                "'GPS__Z' TEXT);"); // 5: GPS_Z
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CAMERA_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CameraInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNAME());
        stmt.bindString(3, entity.getSTD_ID());
 
        String GPS_X = entity.getGPS_X();
        if (GPS_X != null) {
            stmt.bindString(4, GPS_X);
        }
 
        String GPS_Y = entity.getGPS_Y();
        if (GPS_Y != null) {
            stmt.bindString(5, GPS_Y);
        }
 
        String GPS_Z = entity.getGPS_Z();
        if (GPS_Z != null) {
            stmt.bindString(6, GPS_Z);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CameraInfo readEntity(Cursor cursor, int offset) {
        CameraInfo entity = new CameraInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // NAME
            cursor.getString(offset + 2), // STD_ID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // GPS_X
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // GPS_Y
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // GPS_Z
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CameraInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNAME(cursor.getString(offset + 1));
        entity.setSTD_ID(cursor.getString(offset + 2));
        entity.setGPS_X(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGPS_Y(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGPS_Z(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CameraInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CameraInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
