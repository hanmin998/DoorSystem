package com.example.door.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.adapter.base.AdapterBase;

public class ActivitySpl extends ActivityBase{
	private Handler handler;
	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.layout_welcome;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		handler=new Handler();
		handler.postDelayed(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				openActivity(ActivityMainMenu.class);
				finish();
			}
		}, 400);
	
	}
	

}
