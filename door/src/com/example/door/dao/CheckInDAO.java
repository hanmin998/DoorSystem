package com.example.door.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.door.entity.CheckIn;
import com.example.door.entity.User;

/**
 * �����ݿ������ɾ�Ĳ�
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
		//���ݿ����
		ContentValues values=new ContentValues();
		//�����ݷ�װ��������
		values.put("name", checkIn.getName());
		values.put("time", checkIn.getTime());
		//��ȡ���ݿ�
		SQLiteDatabase DB=helper.getWritableDatabase();
		//�����ݿ��в�������
		int id=(int) DB.insert("checkin", null, values);
		return id;
	}
	
	public ArrayList<CheckIn> findAll() {
		ArrayList<CheckIn> list=new ArrayList<CheckIn>();
		//��ȡ���ݿ�
		SQLiteDatabase DB=helper.getReadableDatabase();
		//��ȡ������α�
		Cursor cursor=DB.query("checkin", null, null, null, null, null, null);
		//�ƶ��α�����������
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
