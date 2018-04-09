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
	<title>PE本棚管理システム</title>
</head>
<body>
	<div id="wrapper">
		<h3>PE本棚管理システム</h3>
		<div id="searchForm">
		</div>
		<div id="searchResultWrapper">
				<input type="checkbox" id="copyData" /><label for="copyData">チェック付ける</label>
		</div>
		<div id="btnWrapper">
			<html:form action="/add">
				<html:hidden property="title" />
				<html:hidden property="volume" />
				<html:hidden property="publisher" />
				<html:hidden property="author" />
				<html:hidden property="copyData" />
				<html:submit property="submitBtn" styleId="addBtn" value="追加" />
			</html:form>
		</div>
	</div>

	<script type="text/javascript">
	$(function(){
		$('#addBtn').click(function(){
			if($('#copyData').prop('checked')){
				$('[name="title"]').val('テスト');
				$('[name="volume"]').val('1');
				$('[name="publisher"]').val('テスト社');
				$('[name="author"]').val('テステス子');
				$('[name="copyData"]').val('true');
			}
		});
	});
	</script>
</body>
</html>