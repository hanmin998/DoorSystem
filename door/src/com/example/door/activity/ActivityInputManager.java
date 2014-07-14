package com.example.door.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.entity.Manager;
import com.example.door.view.LockScreenView;

public class ActivityInputManager extends ActivityBase {
	private EditText editText;
	private Button button;
	private LockScreenView lockScreenView;
	private String managerName;
	private String figureNo;

	@Override
	protected int getLayoutID() {

		return R.layout.activity_input_manager;
	}

	@Override
	protected void initView() {
		editText = (EditText) findViewById(R.id.editTextManagerName);
		button = (Button) findViewById(R.id.btn_lock_nopin);
		lockScreenView = (LockScreenView) findViewById(R.id.lockScreenView1);
		button.setEnabled(true);

		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				managerName = editText.getText().toString().trim();
				if (TextUtils.isEmpty(managerName)) {
					showToast("请输入用户名");
					return;
				}
				figureNo = lockScreenView.getLockPin();
				if (figureNo.equals("")) {
					showToast("请输入手势");
					return;
				}
				Manager manager = new Manager(managerName, figureNo);
				doorSystem.getUsers().add(manager);
				openActivity(ActivityInputEmployee.class);
				showToast("添加成功");
				finish();
			}
		});

	}

}
