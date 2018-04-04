<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<html:errors />

	<html:form action="/send" focus="title">
		タイトル:<html:text property="title" size="50" /><br>
		巻数:<html:text property="volume" size="3" /><br>
		出版社:<html:text property="publisher" size="50" /><br>
		貸出状況:<html:checkbox property="status" /><br>
		<html:submit property="submit" value="送信" />
	</html:form>

</body>
</html>