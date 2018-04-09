<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/view/css/common.css">
	<script src="<%= request.getContextPath() %>/view/js/jquery-3.3.1.min.js"></script>
	<title>貸出状態管理画面</title>
</head>
<body>
	<div id="wrapper">
		<html:form action="/detail">
		<h3><bean:write name="DetailForm" property="title"/></h3>
		<div id="searchResultWrapper">
			<table>
				<logic:iterate id="stockList" name="DetailForm" property="stockList">
				</logic:iterate>
			</table>
		</div>
		</html:form>
		<div id="btnWrapper">

		</div>
	</div>

	<script type="text/javascript">
	$(function(){

	});
	</script>
</body>
</html>