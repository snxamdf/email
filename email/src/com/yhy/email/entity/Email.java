package com.yhy.email.entity;

import java.util.List;

import lombok.Data;

@Data
public class Email {
	private String emailId;
	private String recipient;
	private List<String> secondsToSend;
	private List<String> fileListPath;
	private String title;
	private String content;

}
