package com.example.door.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.door.business.DoorSystem;
import com.example.door.dao.CheckInDAO;
import com.example.door.dao.CheckInHelper;
import com.example.door.entity.CheckIn;
/**
 * activity父类
 * @author Administrator
 *
 */
public abstract class ActivityBase extends Activity {
	protected static final int ADMIN_ENTRY_REQUEST_CODE=100;
	protected static final int ADMIN_OPEN_RESULT_CODE=100;
	protected static final int EMPLOYEE_PHOTO_REQUEST_CODE=102;
	protected static final int EMPLOYEE_REQUEST_CODE=103;
	protected static final int EMPLOYEE_ENTER_REQUEST_CODE=104;
	protected static final int MANAGER_ENTER_REQUEST_CODE=105;
	protected DoorSystem doorSystem;
	private SharedPreferences sharedPreferences;
	private Editor editor;
	protected CheckInDAO dao;
	protected CheckIn checkIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
		editor=sharedPreferences.edit();
		setContentView(getLayoutID());
		doorSystem=DoorSystem.getInstance();
		dao=new CheckInDAO(new CheckInHelper(this));
		initView();
		
		editor.putBoolean("isFirst", true);
		editor.commit();
	}
	
	/**
	 * 实现Activity的跳转
	 * @param cla
	 */
	
	protected void openActivity(Class cla){
		startActivity(new Intent(this,cla));
	}
	
	/**
	 * 反向传值
	 * @param cla
	 */
	
	protected void openActivityForResult(Class cla,int requestCode){
		startActivityForResult(new Intent(this,cla),requestCode);
	}
	
	/**
	 * 显示Toast信息
	 * @param msg
	 */
	
	protected void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 抽象方法，子类实现
	 * @return
	 */
	
	protected abstract int getLayoutID();
	
	/**
	 * 初始化控件
	 */
	
	protected abstract void initView();
	//判断是否是第一次启动
	//偏好设置
	protected boolean isFirst() {
		boolean isFirst=sharedPreferences.getBoolean("isFirst", false);
		return isFirst;
	}
}
