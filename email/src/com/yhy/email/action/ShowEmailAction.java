package com.yhy.email.action;

import sc.yhy.annotation.request.Action;
import sc.yhy.annotation.request.RequestMapping;

@Action
@RequestMapping(value = "/email/show")
public class ShowEmailAction {

	@RequestMapping(value = "/showEamil")
	public String getEmail() {
		return "";
	}

	@RequestMapping(value = "/addEmail")
	public String saveEmail() {
		return "";
	}
}
