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
		// ��ʾһ���Ի���
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
					//¼���¹�Ա
					openActivity(ActivityInputEmployee.class);
					break;
				case 1:
					// ���Ž���
					setResult(ADMIN_OPEN_RESULT_CODE);
					finish();
					break;
				case 2:
					//���ݹ�Ա��Ϣ
					//��������,���ļ��������ݿ�
					//xml�ļ���ȡ				
					boolean flag = XMLTools.writeXML(path, doorSystem.getUsers());
					if (flag) {
						showToast("д��ɹ�");
					}else{
						showToast("д��ʧ��");
					}
					
					break;
				case 3:
					//�ָ���Ա��Ϣ
					//�ָ�����
					ArrayList<User> users=XMLTools.readXML(path);
					if(users!=null){
						doorSystem.setUsers(users);
						showToast("�ָ��ɹ�");
					}else{
						showToast("�ָ�ʧ��");
					}
					break;
				case 4:
					//����ָ��¼��
					openActivity(ActivityInputManager.class);
					break;
				case 5:
					//ϵͳ����
					break;
				case 6:
					//����Ա�˳�
					doorSystem.setLogin(false);
					finish();
					break;
				case 7:
					//�鿴������Ϣ
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
							showToast("��½�ɹ�");
							doorSystem.setLogin(true);
						} else {
							showToast("��¼ʧ��");
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
