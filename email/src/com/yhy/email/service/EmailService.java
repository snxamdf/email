package com.yhy.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import sc.yhy.annotation.Autowired;

import com.yhy.email.EmailSender;
import com.yhy.email.MailSenderFactory;
import com.yhy.email.dao.EmailDao;
import com.yhy.email.entity.Email;

public class EmailService {

	@Autowired
	private EmailDao emailDao;

	public void send(Email email) {
		emailDao.print();
		// 发送邮件
		EmailSender sms = MailSenderFactory.getSender();
		try {
			sms.send(email);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
