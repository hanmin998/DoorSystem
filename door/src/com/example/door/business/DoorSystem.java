package com.example.door.business;

import java.util.ArrayList;

import com.example.door.entity.Admin;
import com.example.door.entity.CheckIn;
import com.example.door.entity.Door;
import com.example.door.entity.Ring;
import com.example.door.entity.User;

/**
 * 业务处理类
 * @author Administrator
 *
 */
public class DoorSystem {
	private ArrayList<User> users;
	private ArrayList<CheckIn> checkIn;
	private Door door;
	private Ring ring;
	private static DoorSystem doorSystem;
	
	private boolean isLogin;
	
	private DoorSystem() {
		super();
		isLogin = false;
		users=new ArrayList<User>();
		users.add(new Admin("admin","123456"));
		door=new Door();
		ring=new Ring();
	}
	
	
	
	
	public boolean isLogin() {
		return isLogin;
	}




	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}




	public ArrayList<CheckIn> getCheckIn() {
		return checkIn;
	}




	public void setCheckIn(ArrayList<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}




	public ArrayList<User> getUsers() {
		return users;
	}
	public Door getDoor() {
		return door;
	}
	public Ring getRing() {
		return ring;
	}
	//单例模式
	public static DoorSystem getInstance(){
		if(doorSystem==null){
			doorSystem=new DoorSystem();
		}
		return doorSystem;
	}
	//验证
	public boolean check(Validate validate){
		//用户不同，验证方式也不同
		return validate.checkIn(users);
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
