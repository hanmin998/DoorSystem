package com.example.door.entity;

public class Admin extends User{
	private String password;

	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String name,String password) {
		super(name);
		this.password=password;
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
