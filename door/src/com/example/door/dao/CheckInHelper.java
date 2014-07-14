package com.example.door.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CheckInHelper extends SQLiteOpenHelper {

	public CheckInHelper(Context context) {
		//checkInDB为数据库名,null为游标默认,后面是数据库版本号
		super(context, "checkInDB", null, 1);
		// TODO Auto-generated constructor stub
	}

	//数据库创建时调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建表
		String sql="create table checkin (id integer primary key autoincrement,name varchar (20),time varchar (100))";
		db.execSQL(sql);

	}

	//版本号变动时被调用
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
