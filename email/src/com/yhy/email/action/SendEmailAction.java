package com.yhy.email.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sc.yhy.annotation.Autowired;
import sc.yhy.annotation.request.Action;
import sc.yhy.annotation.request.RequestBody;
import sc.yhy.annotation.request.RequestMapping;
import sc.yhy.annotation.request.RequestParam;

import com.yhy.email.entity.Email;
import com.yhy.email.service.EmailService;

@Action
@RequestMapping(value = "/email/send")
public class SendEmailAction {
	@Autowired
	private EmailService emailService;

	@RequestParam
	private Email email;

	@RequestMapping(value = "/index.action")
	public String toIndex(HttpServletRequest request) {
		request.setAttribute("email", email);
		return "/index.jsp";
	}

	@RequestMapping(value = "/toEamil.action")
	public String toEmail(HttpServletRequest request) {
		List<String> secondsToSend=new ArrayList<String>();
		secondsToSend.add("122798224@qq.com");
		secondsToSend.add("snxamdf@qq.com");
		email.setSecondsToSend(secondsToSend);
		emailService.send(email);
		request.setAttribute("email", email);
		return "/index.jsp";
	}

	@RequestBody
	@RequestMapping(value = "/sendEmail.action")
	public String sendEmail() {
		return "/sendEmail.jsp";
	}
}
