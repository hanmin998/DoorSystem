package com.example.door.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.door.entity.CheckIn;
import com.example.door.entity.User;

/**
 * 对数据库进行增删改查
 * @author Administrator
 *
 */
public class CheckInDAO {
	private CheckInHelper helper;

	public CheckInDAO(CheckInHelper helper) {
		super();
		// TODO Auto-generated constructor stub
		this.helper=helper;
	}
	public int insert(CheckIn checkIn) {
		//数据库操作
		ContentValues values=new ContentValues();
		//把数据封装到操作类
		values.put("name", checkIn.getName());
		values.put("time", checkIn.getTime());
		//获取数据库
		SQLiteDatabase DB=helper.getWritableDatabase();
		//向数据库中插入数据
		int id=(int) DB.insert("checkin", null, values);
		return id;
	}
	
	public ArrayList<CheckIn> findAll() {
		ArrayList<CheckIn> list=new ArrayList<CheckIn>();
		//获取数据库
		SQLiteDatabase DB=helper.getReadableDatabase();
		//获取结果的游标
		Cursor cursor=DB.query("checkin", null, null, null, null, null, null);
		//移动游标查出所有数据
		while (cursor.moveToNext()) {
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String time=cursor.getString(cursor.getColumnIndex("time"));
			list.add(new CheckIn(name,time));
		}
		return list;
	}
	public void delete(String name){
		SQLiteDatabase DB=helper.getWritableDatabase();
		String[] names={name};
		DB.delete("checkin", "name=?", names);
	}
	

}
