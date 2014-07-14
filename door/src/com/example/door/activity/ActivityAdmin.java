package com.example.door.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.business.Validate;
import com.example.door.entity.Admin;
import com.example.door.entity.User;
import com.example.door.utils.XMLTools;

public class ActivityAdmin extends ActivityBase {
	private ListView listView;
	private String path=Environment.getExternalStorageDirectory()+"/users.xml";


	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_admin;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		// 显示一个对话框
		if(!doorSystem.isLogin())
		{
			showLoginDialog();
		}
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.admin_function)));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					//录入新雇员
					openActivity(ActivityInputEmployee.class);
					break;
				case 1:
					// 开门进入
					setResult(ADMIN_OPEN_RESULT_CODE);
					finish();
					break;
				case 2:
					//备份雇员信息
					//备份数据,存文件，存数据库
					//xml文件存取				
					boolean flag = XMLTools.writeXML(path, doorSystem.getUsers());
					if (flag) {
						showToast("写入成功");
					}else{
						showToast("写入失败");
					}
					
					break;
				case 3:
					//恢复雇员信息
					//恢复数据
					ArrayList<User> users=XMLTools.readXML(path);
					if(users!=null){
						doorSystem.setUsers(users);
						showToast("恢复成功");
					}else{
						showToast("恢复失败");
					}
					break;
				case 4:
					//经理指纹录入
					openActivity(ActivityInputManager.class);
					break;
				case 5:
					//系统设置
					break;
				case 6:
					//管理员退出
					doorSystem.setLogin(false);
					finish();
					break;
				case 7:
					//查看考勤信息
					openActivity(ActivityShowCheckIn.class);
					break;

				default:
					break;
					
				}
			}
		});

	}

	private void showLoginDialog() {
		final View view = LayoutInflater.from(this).inflate(
				R.layout.login_dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.admin).setTitle(R.string.admin_login_dialog)
				.setView(view)
				.setPositiveButton(R.string.ok, new OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						final String name = ((EditText) view
								.findViewById(R.id.login_edit_account))
								.getText().toString().trim();
						final String pwd = ((EditText) view
								.findViewById(R.id.login_edit_pwd)).getText()
								.toString().trim();
						Log.i("mytag", "input:" + name);
						Log.i("mytag", "pwd:" + pwd);
						boolean checkIn = doorSystem.check(new Validate() {

							public boolean checkIn(ArrayList<User> users) {
								// TODO Auto-generated method stub
								Admin admin;
								for (User user : users) {
									if (user instanceof Admin) {
										admin = (Admin) user;
										Log.i("mytag",
												"input1:" + admin.getName());
										Log.i("mytag",
												"pwd1:" + admin.getPassword());
										if (admin.getName().equals(name)
												&& admin.getPassword().equals(
														pwd)) {
											return true;
										}
									}
								}
								return false;
							}
						});
						if (checkIn) {
							showToast("登陆成功");
							doorSystem.setLogin(true);
						} else {
							showToast("登录失败");
							finish();
						}
					}
				}).setNegativeButton(R.string.cancel, new OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						finish();
					}
				}).setCancelable(true).show();
	}

}
