package com.example.door.adapter;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.door.R;
import com.example.door.adapter.base.AdapterBase;
import com.example.door.business.ImageTools;
import com.example.door.entity.Admin;
import com.example.door.entity.Employee;
import com.example.door.entity.Manager;
import com.example.door.entity.User;

public class AdapterEmployee extends AdapterBase<User> {

	public AdapterEmployee(Context context, int resourceID, ArrayList<User> data) {
		super(context, resourceID, data);
		// TODO Auto-generated constructor stub
	}

	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHold hold=null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resourceID, null);
			hold = new ViewHold();
			hold.userImageView = (ImageView) view
					.findViewById(R.id.imageViewListItemPhoto);
			hold.userNameTextView = (TextView) view
					.findViewById(R.id.textViewListItemName);
			hold.userTitleTextView = (TextView) view
					.findViewById(R.id.textViewListItemID);
			view.setTag(hold);
		} else {
			hold = (ViewHold) view.getTag();
		}
		User user = data.get(position);
		
		if (user instanceof Admin) {
			hold.userTitleTextView.setText("管理员");
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			if (!employee.isCardHave()) {
				hold.userNameTextView.setTextColor(Color.RED);
			}
			hold.userTitleTextView.setText("ID:"+employee.getCardNo());
			Bitmap bm = ImageTools.scaleImage(employee.getPhoto(), 200, 200);
			
			if (bm != null) {
				hold.userImageView.setImageBitmap(bm);
			}

		} else if (user instanceof Manager) {
			Manager manager=(Manager) user;
			if(!manager.isFigureHave()){
				hold.userNameTextView.setTextColor(Color.RED);
			}
			hold.userTitleTextView.setText("经理");
			hold.userTitleTextView.setText("Figure:"+manager.getFigureNo());		
		}
		hold.userNameTextView.setText(data.get(position).getName());
		return view;
	}

	protected class ViewHold {
		ImageView userImageView;
		TextView userNameTextView;
		TextView userTitleTextView;
	}

}
