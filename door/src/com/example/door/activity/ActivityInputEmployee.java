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
	//��ʱ�����洢Ҫɾ����λ��
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
				//��ʾһ�������Ĳ˵�
				showOptionMenu(position);
				return false;
			}

			protected void showOptionMenu(final int position) {
				currentPosition=position;
				// TODO Auto-generated method stub
				//��ʾlistView����Ŀ�˵�����
				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {						
					public void onCreateContextMenu(ContextMenu menu, View arg1,
							ContextMenuInfo arg2) {
						// TODO Auto-generated method stub
						//�ж�Ա���Ƿ��ֿ�
						User user=doorSystem.getUsers().get(position);
						if (user instanceof Employee) {
							Employee employee=(Employee) user;
							if(employee.isCardHave()){
								menu.add(0,0,0,"ɾ��");
							}else{
								menu.add(0,0,0,"ɾ��");
								menu.add(0,1,0,"�ƿ�");
							}
						}
						if(user instanceof Manager){
							Manager manager=(Manager) user;
							if(manager.isFigureHave()){
								menu.add(0,0,0,"ɾ��");
							}else{
								menu.add(0,0,0,"ɾ��");
								menu.add(0,1,0,"��������");
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
			showToast("�������û���");
			return;
		}
		Intent intent;
		Uri uri;
		employeeID = UUID.randomUUID().toString();
		employeePhoto = Environment.getExternalStorageDirectory() + "/"
				+ System.currentTimeMillis() + ".jpg";
		switch (arg0.getId()) {
			//���ջ��
			case R.id.buttonEmpolyeePhoto:
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				uri = Uri.fromFile(new File(employeePhoto));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, EMPLOYEE_PHOTO_REQUEST_CODE);
				break;
			//���ֻ��ڴ濨��ȡͼƬ
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
	//��Ӳ˵������¼���Ӧ
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case 0:
				//ɾ������ʾɾ���Ի���
				showDeleteDialog();
				
				break;
			case 1:
				//�ƿ�
				
				break;
	
			default:
				break;
		}
		return super.onContextItemSelected(item);
	}

	private void showDeleteDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("�Ƿ�ɾ��")
		.setPositiveButton("ɾ��", new android.content.DialogInterface.OnClickListener(){

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				User user=doorSystem.getUsers().get(currentPosition);
				if(user instanceof Employee){
					//ɾ��ͼƬ
					Employee employee=(Employee) user;
					File file=new File(employee.getPhoto());
					file.delete();
				}
				//ɾ���û�
				doorSystem.getUsers().remove(currentPosition);
				adapter.notifyDataSetChanged();
				
			}
			
		})
		.show();
		
	}

}
