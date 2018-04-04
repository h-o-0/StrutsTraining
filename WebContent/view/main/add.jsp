<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/StrutsTest/view/css/common.css">
	<title>追加画面</title>
</head>
<body>
	<div id="wrapper">
		<h3>追加</h3>
		<html:form action="/add" focus="title">
			<table>
				<tr>
					<td></td>
					<td><html:errors property="title" /></td>
				</tr>
				<tr>
					<td>タイトル</td>
					<td><html:text property="title" size="50" /></td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="volume" /></td>
				</tr>
				<tr>
					<td>巻</td>
					<td><html:text property="volume" size="10" /><br>
					「[数字]-[数字]」 を入力することで、その間にある複数巻を登録できます<br>
					　(例) 1-15　→ 1巻から15巻を登録</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="publisher" /></td>
				</tr>
				<tr>
					<td>出版社</td>
					<td><html:text property="publisher" size="50" /></td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="author" /></td>
				</tr>
				<tr>
					<td>著者</td>
					<td><html:text property="author" size="50" /></td>
				</tr>
			</table>
			<html:button property="bt" value="戻る" onclick="history.back();" />
			<html:submit property="submit" value="登録" />
		</html:form>
	</div>
</body>
</html>