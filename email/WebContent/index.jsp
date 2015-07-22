<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.scyhytag.com/tags/scyhytag" prefix="sc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
</head>
<body>
	<form action="/email/email/send/toEamil.action" method="post">
		<div style="width: 720px; margin-left: 300px;">
			<p>收件人:<input type="text" id="recipient" name="email.recipient" value="${email.recipient }" style="width: 80%;" /></p>
			<sc:each var="l" items="${email.secondsToSend }">
			<p>&nbsp;秒送:<input type="text" id="secondsToSend" name="email.secondsToSend" value="${l}" style="width: 80%;" /></p>
			</sc:each>
			<p>&nbsp;主题:<input type="text" id="title" name="email.title" value="${email.title }" style="width: 80%;" /></p>
			<p>&nbsp;内容:<textarea name="email.content" style="width: 100%;height: 200px;">${email.content}</textarea></p>
			<p><input type="submit" value="发送"/></p>
		</div>
	</form>
</body>
</html>