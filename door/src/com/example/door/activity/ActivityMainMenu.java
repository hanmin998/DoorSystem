package com.example.door.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.adapter.AdapterMainMenu;
import com.example.door.entity.GridviewItem;

public class ActivityMainMenu extends ActivityBase {
	private GridView gridView;
	private ImageView imageView;

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub

		return R.layout.layout_mainmenu;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		gridView = (GridView) findViewById(R.id.gridView1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		ArrayList<GridviewItem> item = new ArrayList<GridviewItem>();
		item.add(new GridviewItem("����Ա����", R.drawable.admin));
		item.add(new GridviewItem("�ÿͰ�����", R.drawable.bell));
		item.add(new GridviewItem("��Աˢ��", R.drawable.card));
		item.add(new GridviewItem("����ָ��", R.drawable.finger));
		AdapterMainMenu adapter = new AdapterMainMenu(this, 0, item);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					// �������Ա
					openActivityForResult(ActivityAdmin.class,
							ADMIN_ENTRY_REQUEST_CODE);
					break;
				case 1:
					// ���������
					doorSystem.getRing().Ring(ActivityMainMenu.this);
					break;
				case 2:
					// ��Աˢ������

					openActivityForResult(ActivityMainMenu.class,
							EMPLOYEE_ENTER_REQUEST_CODE);

					break;
				case 3:
					// ָ�ƽ���
					openActivityForResult(ActivityManagerCheckIn.class,
							MANAGER_ENTER_REQUEST_CODE);
					break;

				default:
					break;
				}

			}

		});
		Animation animation = new ScaleAnimation(0.5f, 1, 0.5f, 1);
		animation.setDuration(3000);
		gridView.setLayoutAnimation(new LayoutAnimationController(animation));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADMIN_ENTRY_REQUEST_CODE) {
			if (resultCode == ADMIN_OPEN_RESULT_CODE) {
				doorSystem.getDoor().open(imageView);
			}
		}
		if (requestCode == EMPLOYEE_ENTER_REQUEST_CODE) {
			doorSystem.getDoor().open(imageView);
		}
		if (requestCode == MANAGER_ENTER_REQUEST_CODE && resultCode == RESULT_OK) {
			doorSystem.getDoor().open(imageView);
		}
	}

}
