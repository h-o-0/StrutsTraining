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
		<div id="stockListWrapper">
			<html:errors property="delete" />
			<html:errors property="lend" />
			<table id="stockList">
				<logic:iterate id="stockList" name="DetailForm" property="stockList" indexId="index">
					<%
					int startNum = 10*((int)index/10);
					int endNum = 10*((int)index/10+1);
					%>
					<logic:equal name="index" value="<%= String.valueOf(startNum) %>">
						<tr>
					</logic:equal>

					<logic:equal name="stockList" property="status" value="0">
						<td class="noStock"><bean:write name="stockList" property="volume"/></td>
					</logic:equal>
					<logic:notEqual name="stockList" property="status" value="0">
						<td class="stock"><bean:write name="stockList" property="volume"/></td>
					</logic:notEqual>

					<logic:equal name="index" value="<%= String.valueOf(endNum) %>">
						</tr>
					</logic:equal>

				</logic:iterate>
			</table>
		</div>
		<div class="btnWrapper">
			<html:hidden property="selectList"/>
			<html:hidden property="title"/>
			<html:submit property="deleteBtn" value="削除" />
			<html:submit property="lendBtn" value="貸出/返却" styleClass="rightBtn" />
		</div>
		</html:form>
	</div>

	<script type="text/javascript">
	$(function(){
		$('#stockList td').click(function(){
			if($(this).hasClass('selected')){
				$(this).removeClass('selected');
			}else{
				$(this).addClass('selected');
			}
		});

		$('[name="deleteBtn"]').click(function(){
			sendSelectList();
			document.DetailForm.action = '<%= request.getContextPath() %>/detail.do?method=delete';
			document.DetailForm.submit();
		});

		$('[name="lendBtn"]').click(function(){
			sendSelectList();
			document.DetailForm.action = '<%= request.getContextPath() %>/detail.do?method=lend';
			document.DetailForm.submit();
		});

		function getSelectList() {
			var selectList = new Array();
			$('#stockList td').each(function(){
				if($(this).hasClass('selected')){
					selectList.push($(this).text());
				}
			});
			selectList = selectList.join(',');
			$('[name="selectList"]').val(selectList);
		}
		function sendSelectList() {
			var selectList = new Array();
			$('#stockList td').each(function(){
				if($(this).hasClass('selected')){
					selectList.push($(this).text());
				}
			});
			selectList = selectList.join(',');
			$('[name="selectList"]').val(selectList);
		}
	});
	</script>
</body>
</html>