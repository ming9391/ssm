package com.ssm.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailUtil{
	
	public static void main(String[] args) {
		try {
			//sendEmail("3337028770@qq.com","etgzxjeepxncdbcj","9391878421@qq.com,3337028770@qq.com");
			sendEmail("3337028770@qq.com","etgzxjeepxncdbcj","3337028770@qq.com","hr入库信息","commission-content-test");
		} catch (MessagingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param formUser 发送人邮箱
	 * @param password 发送人16位授权码
	 * @param to 收件人（多个用逗号隔开）
	 * @param subject 主题
	 * @param content 邮件正文
	 * @throws MessagingException
	 * @throws GeneralSecurityException
	 */
	 public static void sendEmail(String formUser,String password,String to,String subject,String content) throws MessagingException, GeneralSecurityException {
	        //创建一个配置文件并保存
	        Properties properties = new Properties();
	        properties.setProperty("mail.host","smtp.qq.com");
	        properties.setProperty("mail.transport.protocol","smtp");
	        properties.setProperty("mail.smtp.auth","true");

	        //QQ存在一个特性设置SSL加密
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.ssl.socketFactory", sf);

	        //创建一个session对象
	        Session session = Session.getDefaultInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(formUser,password);
	            }
	        });

	        //开启debug模式
	        session.setDebug(true);

	        //获取连接对象
	        Transport transport = session.getTransport();

	        //连接服务器
	        transport.connect("smtp.qq.com",formUser,password);

	        //创建邮件对象
	        MimeMessage mimeMessage = new MimeMessage(session);

	        //邮件发送人
	        mimeMessage.setFrom(new InternetAddress(formUser));

	        //邮件接收人
	       // mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("939187842@qq.com,3337028770@qq.com"));
	        InternetAddress[] sendTo = InternetAddress.parse(to);
	        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, sendTo);
            
	        //邮件标题
	        mimeMessage.setSubject(subject);

	        //邮件内容
	        mimeMessage.setContent(content,"text/html;charset=UTF-8");

	        //发送邮件
	        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

	        //关闭连接
	        transport.close();
	    }

}
