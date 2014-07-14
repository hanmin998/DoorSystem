package com.example.door.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.adapter.AdapterEmployee;
import com.example.door.entity.CheckIn;

public class ActivityManagerEnter extends ActivityBase {
	private ListView listView;
	private EditText editText;
	private Button button_photo, button_pic;
	private AdapterEmployee adapter;


	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_input_employee;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.listViewEmpolyee);
		editText = (EditText) findViewById(R.id.editTextEmpolyeeName);
		editText.setVisibility(View.GONE);
		button_photo = (Button) findViewById(R.id.buttonEmpolyeePhoto);
		button_photo.setVisibility(View.GONE);
		button_pic = (Button) findViewById(R.id.buttonEmpolyeePic);
		button_pic.setVisibility(View.GONE);
		adapter = new AdapterEmployee(this,
				R.layout.activity_input_employee_listitem,
				doorSystem.getUsers());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				//将系统时间和点击的用户名传给数据库
				String name=doorSystem.getUsers().get(position).getName();
				Date date=new Date();
				String time=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(date);
				CheckIn checkIn=new CheckIn(name,time);
				int id=dao.insert(checkIn);
				showToast("你插入第"+id+"条数据");
				openActivity(ActivityMainMenu.class);
				showToast("经理"+name+"进入"+time);
			}
		});

		

	}

}
