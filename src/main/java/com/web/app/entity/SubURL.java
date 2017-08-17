package com.web.app.entity;

import java.io.Serializable;

public class SubURL implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String menuId;
	private String Surl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSurl() {
		return Surl;
	}
	public void setSurl(String surl) {
		Surl = surl;
	}
	
	
}
