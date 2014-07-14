package com.example.door.activity;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.entity.CheckIn;
/**
 * ��ʾ������Ϣ
 * @author Administrator
 *
 */
public class ActivityShowCheckIn extends ActivityBase {
	private ListView listView;

	private ArrayList<CheckIn> checkInList;
	private int currentPosition;
	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_checkin_list;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		listView=(ListView) findViewById(R.id.lv_checkin);
		checkInList=dao.findAll();
		Log.i("MyLog", "checkINList:"+checkInList.size());

		listView.setAdapter(
				new ArrayAdapter<Object>(ActivityShowCheckIn.this,
						android.R.layout.simple_list_item_1,
						dao.findAll().toArray()));
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if(position==0){
					return false;
				}
				//��ʾһ�������Ĳ˵�
				//showOptionMenu(position);
				
				AlertDialog.Builder builder=new AlertDialog.Builder(ActivityShowCheckIn.this);
				builder.setTitle("�Ƿ�ɾ��")
				.setPositiveButton("ɾ��", new android.content.DialogInterface.OnClickListener(){

					public void onClick(DialogInterface arg0, int position) {
						// TODO Auto-generated method stub
						CheckIn checkIn=checkInList.get(position+1);
						Log.i("MyLog", "checkIn"+checkIn.getName());
						dao.delete(checkIn.getName());
						checkInList=dao.findAll();
						listView.setAdapter(
								new ArrayAdapter<CheckIn>(ActivityShowCheckIn.this,
										android.R.layout.simple_list_item_1,
										checkInList));	
						listView.invalidate();
					}
					
				})
				.setNegativeButton("ȡ��", null)
				.show();
				
				return false;

			}
		});
	}
//			private void showOptionMenu(final int position) {
//				// TODO Auto-generated method stub
//				currentPosition=position;
//				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {						
//					public void onCreateContextMenu(ContextMenu menu, View arg1,
//							ContextMenuInfo arg2) {
//						// TODO Auto-generated method stub						
//						menu.add(0,0,0,"ɾ��");
//					}
//				});
//				
//			}
//			
//			@Override
//			public boolean onContextItemSelected(MenuItem item) {
//				// TODO Auto-generated method stub
//				switch (item.getItemId()) {
//					case 0:
//						//ɾ������ʾɾ���Ի���
//						showDeleteDialog();
//						
//						break;		
//					default:
//						break;
//				}
//				return super.onContextItemSelected(item);
//			}
//
//			private void showDeleteDialog() {
//				// TODO Auto-generated method stub
//				AlertDialog.Builder builder=new AlertDialog.Builder(this);
//				builder.setTitle("�Ƿ�ɾ��")
//				.setPositiveButton("ɾ��", new android.content.DialogInterface.OnClickListener(){
//
//					public void onClick(DialogInterface arg0, int position) {
//						// TODO Auto-generated method stub
//						CheckIn checkIn=checkInList.get(position);
//						dao.delete(checkIn.getName());
//						listView.setAdapter(
//								new ArrayAdapter<CheckIn>(ActivityShowCheckIn.this,
//										android.R.layout.simple_list_item_1,
//										checkInList));	
//						listView.invalidate();
//					}
//					
//				})
//				.setNegativeButton("ȡ��", null)
//				.show();
//				
//			}

}
