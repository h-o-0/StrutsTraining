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
	<title>巻数追加画面</title>
</head>
<body>
	<div id="wrapper">
		<h3>巻数追加画面</h3>
			<div class="formWrapper">
				<html:form action="/add">
					<table id="libraryDetail">
						<tr>
							<td>タイトル</td>
							<td><bean:write name="AddForm" property="library.title" /></td>
						</tr>
						<tr>
							<td>出版社</td>
							<td><bean:write name="AddForm" property="library.publisher" /></td>
						</tr>
						<tr>
							<td>著者</td>
							<td><bean:write name="AddForm" property="library.author" /></td>
						</tr>
					</table>

					<br>

					<html:errors property="volume" />
					<p>登録する巻を入力してください</p>
					<html:text property="volume" /><br>
					<p>※「[数字]-[数字]」 を入力することで、その間にある複数巻を登録できます<br>
					　(例) 1-15　→ 1巻から15巻を登録</p>

					<br>

				</html:form>
			</div>
			<div class="btnWrapper">
				<html:form action="/detail">
					<html:hidden name="AddForm" property="id" />
					<html:submit value="戻る" />
				</html:form>
				<html:form action="/add?method=regist" styleClass="rightBtn">
					<html:hidden name="AddForm" property="id" />
					<html:hidden name="AddForm" property="volume" />
					<html:submit property="submitBtn" value="登録" />
				</html:form>
			</div>
	</div>

	<script type="text/javascript">
	$(function(){
		var registComplete = <%= request.getAttribute("registComplete") %>;

		$('[name="submitBtn"]').click(function(){
			$('[name="volume"][type="hidden"]').val($('[name="volume"]').val());
		});

		if(registComplete == false){
			alert('処理に失敗しました。');
		}
	});
	</script>
</body>
</html>