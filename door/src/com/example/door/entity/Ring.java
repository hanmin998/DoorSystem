package com.example.door.entity;


import android.content.Context;
import android.media.MediaPlayer;

import com.example.door.R;

public class Ring {
	private boolean ringOrNot;


	public Ring() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void Ring(Context context) {
		MediaPlayer mediaPlayer=MediaPlayer.create(context, R.raw.ring);
		mediaPlayer.start();

	}
	public Ring(boolean ringOrNot) {
		super();
		// TODO Auto-generated constructor stub
		this.ringOrNot=ringOrNot;
	}
	public boolean isRingOrNot() {
		return ringOrNot;
	}
	public void setRingOrNot(boolean ringOrNot) {
		this.ringOrNot = ringOrNot;
	}
	
}
