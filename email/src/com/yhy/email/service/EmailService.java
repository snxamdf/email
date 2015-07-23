package com.yhy.email.service;

import java.util.ArrayList;
import java.util.List;

import sc.yhy.annotation.Autowired;

import com.yhy.email.entity.Email;
import com.yhy.email.sender.MailWithAttachment;

public class EmailService {
	@Autowired
	private MailWithAttachment attachment;

	public void send(Email email) {
		MailWithAttachment se = new MailWithAttachment(true);
		List<String> fileListPath = new ArrayList<String>();
		fileListPath.add(new String("D:\\300.jpg"));
		fileListPath.add(new String("D:\\333.jpg"));

		List<String> secondsToSend = new ArrayList<String>();
		secondsToSend.add("qqqqq@qq.com");
		//se.doSendHtmlEmail("邮件主题", "邮件内容", "qqqqq@qq.com", secondsToSend, fileListPath);
	}

}
