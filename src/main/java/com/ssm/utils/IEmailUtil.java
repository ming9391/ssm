package com.ssm.utils;

import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

public interface IEmailUtil {

  public void sendEmail(String formUser,String password,String to,String subject,String content) throws MessagingException,GeneralSecurityException;
	
}
