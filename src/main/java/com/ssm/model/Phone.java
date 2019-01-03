package com.ssm.model;

import java.io.Serializable;

public class Phone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String phoneId;
	private String phone;
	
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
