package com.example.door.activity;

import java.io.File;
import java.util.UUID;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.adapter.AdapterEmployee;
import com.example.door.entity.Employee;
import com.example.door.entity.Manager;
import com.example.door.entity.User;

public class ActivityInputEmployee extends ActivityBase implements
		OnClickListener {
	private ListView listView;
	private EditText editText;
	private Button button_photo, button_pic;
	private AdapterEmployee adapter;
	private String employeeName;
	private String employeeID;
	private String employeePhoto;
	//临时变量存储要删除的位置
	private int currentPosition;

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
		button_photo = (Button) findViewById(R.id.buttonEmpolyeePhoto);
		button_pic = (Button) findViewById(R.id.buttonEmpolyeePic);
		adapter = new AdapterEmployee(this,
				R.layout.activity_input_employee_listitem,
				doorSystem.getUsers());
		listView.setAdapter(adapter);
		button_photo.setOnClickListener(this);
		button_pic.setOnClickListener(this);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if(position==0){
					return false;
				}
				//显示一个上下文菜单
				showOptionMenu(position);
				return false;
			}

			protected void showOptionMenu(final int position) {
				currentPosition=position;
				// TODO Auto-generated method stub
				//显示listView的条目菜单监听
				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {						
					public void onCreateContextMenu(ContextMenu menu, View arg1,
							ContextMenuInfo arg2) {
						// TODO Auto-generated method stub
						//判断员工是否又卡
						User user=doorSystem.getUsers().get(position);
						if (user instanceof Employee) {
							Employee employee=(Employee) user;
							if(employee.isCardHave()){
								menu.add(0,0,0,"删除");
							}else{
								menu.add(0,0,0,"删除");
								menu.add(0,1,0,"制卡");
							}
						}
						if(user instanceof Manager){
							Manager manager=(Manager) user;
							if(manager.isFigureHave()){
								menu.add(0,0,0,"删除");
							}else{
								menu.add(0,0,0,"删除");
								menu.add(0,1,0,"更改手势");
							}
						}
					}
				});
			}
		});

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		employeeName = editText.getText().toString().trim();
		if (TextUtils.isEmpty(employeeName)) {
			showToast("请输入用户名");
			return;
		}
		Intent intent;
		Uri uri;
		employeeID = UUID.randomUUID().toString();
		employeePhoto = Environment.getExternalStorageDirectory() + "/"
				+ System.currentTimeMillis() + ".jpg";
		switch (arg0.getId()) {
			//拍照获得
			case R.id.buttonEmpolyeePhoto:
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				uri = Uri.fromFile(new File(employeePhoto));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, EMPLOYEE_PHOTO_REQUEST_CODE);
				break;
			//从手机内存卡获取图片
			 case R.id.buttonEmpolyeePic:
				intent = new Intent();
				intent.setAction(Intent.ACTION_PICK);
				intent.setType("image/*");
				startActivityForResult(intent, EMPLOYEE_REQUEST_CODE);
				break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == EMPLOYEE_PHOTO_REQUEST_CODE
				&& resultCode == RESULT_OK) {
			Employee employee = new Employee(employeeName, employeeID,
					employeePhoto);
			doorSystem.getUsers().add(employee);
		}

		if (requestCode == EMPLOYEE_REQUEST_CODE && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			ContentResolver contentResolver=getContentResolver();
			Cursor cursor=contentResolver.query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
			if(cursor!=null){
				cursor.moveToFirst();
				int index=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
				String employeePhoto=cursor.getString(index);
				Employee employee = new Employee(employeeName, employeeID,
						employeePhoto);
				doorSystem.getUsers().add(employee);
				adapter.notifyDataSetChanged();
			}
		}
	}
	//添加菜单监听事件响应
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case 0:
				//删除，显示删除对话框
				showDeleteDialog();
				
				break;
			case 1:
				//制卡
				
				break;
	
			default:
				break;
		}
		return super.onContextItemSelected(item);
	}

	private void showDeleteDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("是否删除")
		.setPositiveButton("删除", new android.content.DialogInterface.OnClickListener(){

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				User user=doorSystem.getUsers().get(currentPosition);
				if(user instanceof Employee){
					//删除图片
					Employee employee=(Employee) user;
					File file=new File(employee.getPhoto());
					file.delete();
				}
				//删除用户
				doorSystem.getUsers().remove(currentPosition);
				adapter.notifyDataSetChanged();
				
			}
			
		})
		.show();
		
	}

}
