package com.example.door.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CheckInHelper extends SQLiteOpenHelper {

	public CheckInHelper(Context context) {
		//checkInDBΪ���ݿ���,nullΪ�α�Ĭ��,���������ݿ�汾��
		super(context, "checkInDB", null, 1);
		// TODO Auto-generated constructor stub
	}

	//���ݿⴴ��ʱ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//������
		String sql="create table checkin (id integer primary key autoincrement,name varchar (20),time varchar (100))";
		db.execSQL(sql);

	}

	//�汾�ű䶯ʱ������
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
