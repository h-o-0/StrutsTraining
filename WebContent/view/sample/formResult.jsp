<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>フォーム送信結果</title>
</head>
<body>
	タイトル：<bean:write name="FormBeanForm" property="title" scope="request" ignore="true" /><br>
	巻数：<bean:write name="FormBeanForm" property="volume" scope="request" ignore="true" /><br>
	出版社：<bean:write name="FormBeanForm" property="publisher" scope="request" ignore="true" /><br>
	貸出状況：<bean:write name="FormBeanForm" property="status" scope="request" ignore="true" />
</body>
</html>