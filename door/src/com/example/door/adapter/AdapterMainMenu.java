package com.example.door.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.door.adapter.base.AdapterBase;
import com.example.door.entity.GridviewItem;

public class AdapterMainMenu extends AdapterBase<GridviewItem>{


	public AdapterMainMenu(Context context, int resourceID, ArrayList data) {
		
		super(context, resourceID, data);
		// TODO Auto-generated constructor stub
	}

	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		TextView textView=new TextView(context);
		textView.setText(data.get(position).getName());
		Drawable top=context.getResources().getDrawable(data.get(position).getPic());
		top.setBounds(0,0,50,50);
		textView.setCompoundDrawables(null, top, null, null);
		textView.setGravity(Gravity.CENTER);
		return textView;
	}

}
