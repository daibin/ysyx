package com.ysyx.androidvlc;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tangjun on 14-8-24.
 */
public class VLCApplication extends Application {

	private static VLCApplication sInstance;

	private SQLiteDatabase db;
	private  DaoMaster daoMaster;
	private  DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();

		sInstance = this;
	}

	public static Context getAppContext() {
		return sInstance;
	}

	public  void setupDatabase() {
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "CAMERA_INFO", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
	}

	public  CameraInfoDao getCameraInfoDao() {
		return daoSession.getCameraInfoDao();
	}
}
