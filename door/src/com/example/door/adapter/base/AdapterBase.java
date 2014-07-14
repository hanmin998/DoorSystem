package com.example.door.adapter.base;

import java.util.ArrayList;

import android.content.Context;
import android.widget.BaseAdapter;
/**
 *   ≈‰∆˜
 * @author Administrator
 *
 */
public abstract class AdapterBase<T> extends BaseAdapter {
	protected Context context;
	protected int resourceID;
	protected ArrayList<T> data;

	
	public AdapterBase(Context context,int resourceID,ArrayList<T> data) {
		super();
		// TODO Auto-generated constructor stub
		this.context=context;
		this.resourceID=resourceID;
		this.data=data;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
