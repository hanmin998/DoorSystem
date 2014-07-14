package com.example.door.entity;

public class Employee extends User{
	private String cardNo;
	private boolean cardHave;
	private String photo;

	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name,String cardNo,String photo) {
		super(name);
		this.cardNo=cardNo;
		this.photo=photo;
		// TODO Auto-generated constructor stub
	}
	public Employee(String name,String cardNo,String photo,boolean cardHave) {
		super(name);
		this.cardNo=cardNo;
		this.photo=photo;
		this.cardHave=cardHave;
		// TODO Auto-generated constructor stub
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public boolean isCardHave() {
		return cardHave;
	}

	public void setCardHave(boolean cardHave) {
		this.cardHave = cardHave;
	}


	
}
