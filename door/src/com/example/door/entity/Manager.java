package com.example.door.entity;

public class Manager extends User{
	private String figureNo;
	

	private boolean isFigureHave;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String name,String figureNo) {
		super(name);
		this.figureNo=figureNo;

		// TODO Auto-generated constructor stub
	}
	public String getFigureNo() {
		return figureNo;
	}
	public void setFigureNo(String figureNo) {
		this.figureNo = figureNo;
	}


	
	public boolean isFigureHave() {
		return isFigureHave;
	}

	public void setisFigureHave(boolean isFigureHave) {
		this.isFigureHave = isFigureHave;
	}
}
