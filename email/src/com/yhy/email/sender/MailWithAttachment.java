package com.yhy.email.sender;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailWithAttachment {
	private MimeMessage message;
	private Session session;
	private Transport transport;

	private String mailHost = "";
	private String sender_username = "";
	private String sender_password = "";

	private Properties properties = new Properties();

	public MailWithAttachment() {
		this.MailInit(false);
	}

	public MailWithAttachment(boolean debug) {
		this.MailInit(debug);
	}

	/*
	 * 初始化方法
	 */
	private void MailInit(boolean debug) {

		InputStream in = MailWithAttachment.class.getResourceAsStream("mail-server.properties");
		try {
			properties.load(in);
			this.mailHost = properties.getProperty("mail.smtp.host");
			this.sender_username = properties.getProperty("mail.sender.username");
			this.sender_password = properties.getProperty("mail.sender.password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param sendHtml
	 *            邮件内容
	 * @param receiveUser
	 *            收件人地址
	 * @param attachment
	 *            附件
	 */
	public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, List<String> secondsToSend, List<File> attachments) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(sender_username);
			message.setFrom(from);

			// 收件人
			InternetAddress to = new InternetAddress(receiveUser);
			message.setRecipient(Message.RecipientType.TO, to);

			// 抄送
			if (secondsToSend != null && secondsToSend.size() > 0) {
				// 为每个邮件接收者创建一个地址
				Address[] ccAdresses = new InternetAddress[secondsToSend.size()];
				for (int i = 0; i < secondsToSend.size(); i++) {
					ccAdresses[i] = new InternetAddress(secondsToSend.get(i));
				}
				// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
				message.setRecipients(Message.RecipientType.CC, ccAdresses);
			}

			// 邮件主题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 添加附件的内容
			if (attachments != null && attachments.size() > 0) {
				for (File file : attachments) {
					BodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(file);
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
					multipart.addBodyPart(attachmentBodyPart);
				}
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();

			transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(mailHost, sender_username, sender_password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());

			System.out.println("send success!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		MailWithAttachment se = new MailWithAttachment(true);
		List<File> affix = new ArrayList<File>();
		File file = new File("D:\\300.jpg");
		affix.add(file);
		file = new File("D:\\333.jpg");
		affix.add(file);

		List<String> secondsToSend = new ArrayList<String>();
		secondsToSend.add("qqqqq@qq.com");
		se.doSendHtmlEmail("邮件主题", "邮件内容", "qqqqq@qq.com", secondsToSend, affix);
	}
}
