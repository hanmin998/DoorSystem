package com.example.door.entity;
/**
 * 实体类，存放数据库表信息
 * @author Administrator
 *
 */
public class CheckIn {
	
	private String name;
	private String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public CheckIn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckIn(String name2, String time2) {
		this.name=name2;
		this.time=time2;
		// TODO Auto-generated constructor stub
	}
	

}
