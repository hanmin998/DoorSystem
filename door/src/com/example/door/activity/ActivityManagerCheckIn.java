package com.example.door.activity;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;
import com.example.door.business.Validate;
import com.example.door.entity.Manager;
import com.example.door.entity.User;
import com.example.door.view.LockScreenView;
import com.example.door.view.LockScreenView.onPinDrawFinishListener;

public class ActivityManagerCheckIn extends ActivityBase {
	 private LockScreenView lockScreenView;
	 private EditText editText;
	 private Button button;

	    private String username;
	    private String pin;

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_input_manager;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		lockScreenView=(LockScreenView) findViewById(R.id.lockScreenView1);
		editText=(EditText) findViewById(R.id.editTextManagerName);
		button=(Button) findViewById(R.id.btn_lock_nopin);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(editText.getText().toString().trim()==""){
					showToast("请输入名字");
					return;
				}
				if(lockScreenView.getLockPin().equals("")){
					showToast("请输入手势");
					return;
				}
				
				username=editText.getText().toString();
				pin = lockScreenView.getLockPin();
				
				boolean checkResult=doorSystem.check(new Validate() {
					
					public boolean checkIn(ArrayList<User> users) {
						// TODO Auto-generated method stub
						for (User user : users) {
							if(user instanceof Manager){
								Manager manager=(Manager) user;
								if(manager.getName().equals(username) && manager.getFigureNo().equals(pin));
								return true;
							}
						}
						return false;
					}
				});
            	if (checkResult) {
                    setResult(RESULT_OK);
                    showToast("验证成功");
                    finish();
                } else {
                    setResult(RESULT_CANCELED);
                    showToast("验证失败");
                    finish();
                }

			}
		});

	}

}
