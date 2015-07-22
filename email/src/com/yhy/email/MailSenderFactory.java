package com.yhy.email;

public class MailSenderFactory {
	/**
	 * 服务邮箱
	 */
	private static EmailSender serviceSms = null;

	/**
	 * 获取邮箱
	 * 
	 * @param type
	 *            邮箱类型
	 * @return 符合类型的邮箱
	 */
	public static EmailSender getSender() {
		if (serviceSms == null) {
			serviceSms = new EmailSender("snxamdftest@126.com", "snxamdftest0");
		}
		return serviceSms;
	}
}
