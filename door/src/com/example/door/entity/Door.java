package com.example.door.entity;

import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.door.R;

public class Door {
	private boolean openOrNot;

	public Door() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Door(boolean openOrNot) {
		super();
		// TODO Auto-generated constructor stub
		this.openOrNot=openOrNot;
	}
	public boolean isOpenOrNot() {
		return openOrNot;
	}
	public void setOpenOrNot(boolean openOrNot) {
		this.openOrNot = openOrNot;
	}
	public void open(ImageView image) {
		// TODO Auto-generated method stub
		AnimationDrawable animation=(AnimationDrawable) image.getDrawable();
		animation.stop();
		animation.start();

	}
}
