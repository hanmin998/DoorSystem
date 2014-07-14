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
 * activity����
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
	 * ʵ��Activity����ת
	 * @param cla
	 */
	
	protected void openActivity(Class cla){
		startActivity(new Intent(this,cla));
	}
	
	/**
	 * ����ֵ
	 * @param cla
	 */
	
	protected void openActivityForResult(Class cla,int requestCode){
		startActivityForResult(new Intent(this,cla),requestCode);
	}
	
	/**
	 * ��ʾToast��Ϣ
	 * @param msg
	 */
	
	protected void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * ���󷽷�������ʵ��
	 * @return
	 */
	
	protected abstract int getLayoutID();
	
	/**
	 * ��ʼ���ؼ�
	 */
	
	protected abstract void initView();
	//�ж��Ƿ��ǵ�һ������
	//ƫ������
	protected boolean isFirst() {
		boolean isFirst=sharedPreferences.getBoolean("isFirst", false);
		return isFirst;
	}
}
