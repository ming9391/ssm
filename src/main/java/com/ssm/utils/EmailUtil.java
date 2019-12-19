package com.ssm.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

	public static void main(String[] args) throws MessagingException, GeneralSecurityException, IOException {
		sendEmail("3337028770@qq.com", "etgzxjeepxncdbcj", "3337028770@qq.com", "hr入库信息","commission-content-test","F:/csv/log/hr.txt","hr_message.txt");
	}

	/**
	 *   QQ邮箱发邮件
	 * @param formUser 发送人邮箱
	 * @param password 发送人16位授权码
	 * @param to       收件人（多个用逗号隔开）
	 * @param subject  主题
	 * @param content  邮件正文
	 * @param filePath 附件路径
	 * @param filename 附件名称
	 * @throws MessagingException
	 * @throws GeneralSecurityException
	 * @throws IOException 
	 */
	public static void sendEmail(String formUser, String password, String to, String subject, String content,String filePath,String filename)
			throws MessagingException, GeneralSecurityException, IOException {
		// 创建一个配置文件并保存
		Properties properties = new Properties();
		properties.setProperty("mail.host", "smtp.qq.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");

		//邮箱发送服务器端口,这里设置为 lsof -i:465 587端口
        //properties.setProperty("mail.smtp.port", "465");
        //properties.setProperty("mail.smtp.socketFactory.port", "465");
		
		// QQ存在一个特性设置SSL加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		// 创建一个session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(formUser, password);
			}
		});

		// 开启debug模式
		session.setDebug(true);
		// 获取连接对象
		Transport transport = session.getTransport();
		// 连接服务器
		transport.connect("smtp.qq.com", formUser, password);
		
		// 创建邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件标题
		mimeMessage.setSubject(subject);

		// 邮件发送人
		mimeMessage.setFrom(new InternetAddress(formUser));
		// 邮件接收人
		InternetAddress[] sendTo = InternetAddress.parse(to);
		mimeMessage.setRecipients(MimeMessage.RecipientType.TO, sendTo);
		

		// 邮件内容
		Multipart listPart = new MimeMultipart();
		// 正文
		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent(content,"text/html;charset=UTF-8");
		listPart.addBodyPart(part1);
		mimeMessage.setContent(listPart);
		// 附件
		if(filePath!=null && !"".equals(filePath)) {
			MimeBodyPart part2 = new MimeBodyPart();
			part2.attachFile(filePath);
			part2.setFileName(MimeUtility.encodeText(filename));
			listPart.addBodyPart(part2);
			mimeMessage.setContent(listPart);
		}
		
		// 设置邮件的发送时间,默认立即发送
		mimeMessage.setSentDate(new Date());
		// 发送邮件
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		// 关闭连接
		transport.close();
	}

}
