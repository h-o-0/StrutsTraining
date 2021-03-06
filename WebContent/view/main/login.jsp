<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/view/css/common.css">
	<title>ログイン</title>
</head>
<body>
	<div id="wrapper">
		<h3>PE本棚管理システム</h3>
		<html:form action="/login" focus="userId">
			<div class="inputWrapper">
				ユーザーID <html:errors property="userId" /><br>
				<html:text property="userId" />
			</div>
			<div class="inputWrapper">
				パスワード <html:errors property="password" /><br>
				<html:password property="password" /><br>
			</div>
			<html:submit property="submit" value="ログイン" />
		</html:form>
	</div>
</body>
</html>