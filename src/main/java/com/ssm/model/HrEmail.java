package com.ssm.model;

public class HrEmail {

	public static String formUser = "3337028770@qq.com";
	public static String password = "etgzxjeepxncdbcj";
	public static String subject = "hr入库报文";
	private String sendTo;
	private String sendContent;
	
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
	
}
