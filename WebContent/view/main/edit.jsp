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
	<title>追加画面</title>
</head>
<body>
	<div id="wrapper">
		<html:form action="/edit?method=validate" focus="title">
		<h3>
			<logic:equal name="EditForm" property="isNewBook" value="true">書籍追加</logic:equal>
			<logic:equal name="EditForm" property="isNewBook" value="false">書籍情報編集</logic:equal>
		</h3>
			<div class="formWrapper">
			<table>
				<tr>
					<td></td>
					<td><html:errors property="title" /></td>
				</tr>
				<tr>
					<td>タイトル</td>
					<td>
						<html:text property="title" styleClass="title" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="volume" /></td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="publisher" /></td>
				</tr>
				<tr>
					<td>出版社</td>
					<td>
						<html:text property="publisher" styleClass="publisher" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="author" /></td>
				</tr>
				<tr>
					<td>著者</td>
					<td>
						<html:text property="author" styleClass="author" />
					</td>
				</tr>
			</table>
			</div>
			<div class="btnWrapper">
				<html:hidden property="id" />
				<html:hidden property="isNewBook" />
				<html:button property="toMain" value="戻る" />
				<html:submit property="submitBtn" value="登録" styleClass="rightBtn" />
			</div>
		</html:form>
	</div>

	<script type="text/javascript">
	$(function(){

		var noError = <%= request.getAttribute("noError") %>;
		var registComplete = <%= request.getAttribute("registComplete") %>;

		$('[name="toMain"]').on('click', function(){
			location.href = '<%= request.getContextPath() %>/view/main/main.jsp';
		});

		if(noError && !registComplete){
			var registMsg =
				'以下を登録します。よろしいですか？\n'
				+ '\n'
				+ 'タイトル：' + $('[name="title"]').val() + '\n'
				+ '出版社：' + $('[name="publisher"]').val() + '\n'
				+ '著者：' + $('[name="author"]').val();

			if($('[name="isNewBook"]').val() == 'true'){
				document.EditForm.action = '<%= request.getContextPath() %>/edit.do?method=insertRegist';
			}else{
				document.EditForm.action = '<%= request.getContextPath() %>/edit.do?method=updateRegist';
			}
			if(window.confirm(registMsg)){
				//登録処理
				document.EditForm.submit();
				document.EditForm.action='<%= request.getContextPath() %>/edit.do?method=validate';
			}else{
				//キャンセル
				document.EditForm.action='<%= request.getContextPath() %>/edit.do?method=validate';
			}
		}
		if(registComplete){
			alert('処理が終了しました。');
		}
	});
	</script>
</body>
</html>