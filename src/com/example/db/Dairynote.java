package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Dairynote extends SQLiteOpenHelper {

	private static final String name = "dairynote.db";
	private static final int version = 1; 
	/*
	 * 创建一个数据库
	 */
	public Dairynote(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        db.execSQL("create table if not exists user(_id integer primary key autoincrement," 
		        +"name varchar(20),"
        		+"context varchar(100),"
		        +"time varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
      db.execSQL("drop table if exists user");
		onCreate(db);
	}

}
