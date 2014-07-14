package com.example.door.entity;

public class GridviewItem {

	private String name;
	private int pic;
	public GridviewItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GridviewItem(String string, int icActionSearch) {
		// TODO Auto-generated constructor stub
		this.name=string;
		this.pic=icActionSearch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPic() {
		return pic;
	}
	public void setPic(int pic) {
		this.pic = pic;
	}
	
}
