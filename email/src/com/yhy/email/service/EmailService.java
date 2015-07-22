package com.yhy.email.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sc.yhy.annotation.Autowired;

import com.yhy.email.entity.Email;
import com.yhy.email.sender.MailWithAttachment;

public class EmailService {
	@Autowired
	private MailWithAttachment attachment;

	public void send(Email email) {
		List<File> affix = new ArrayList<File>();
		File file = new File("D:\\300.jpg");
		affix.add(file);
		file = new File("D:\\333.jpg");
		affix.add(file);

		List<String> secondsToSend = new ArrayList<String>();
		secondsToSend.add("qqqqq@qq.com");
		attachment.doSendHtmlEmail("邮件主题", "邮件内容", "qqqqq@qq.com", secondsToSend, affix);
	}

}
