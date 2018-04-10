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
		<h3>書籍追加</h3>
		<html:form action="/add?method=validate" focus="title">
			<table>
				<tr>
					<td></td>
					<td><html:errors property="title" /></td>
				</tr>
				<tr>
					<td>タイトル</td>
					<td>
						<input list="titleList" id="title" name="title" autocomplete="off" value="<bean:write name="AddForm" property="title" />">
						<dataList id="titleList">
							<logic:iterate id="titleList" name="AddForm" property="titleList">
								<option value="<bean:write name="titleList" />" />
							</logic:iterate>
						</dataList>
						<html:hidden property="title" styleClass="title" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="volume" /></td>
				</tr>
				<tr>
					<td>巻</td>
					<td>
						<input list="volumeList" id="volume" name="volume" autocomplete="off" value="<bean:write name="AddForm" property="volume" />">
						<dataList id="volumeList">
							<logic:iterate id="volumeList" name="AddForm" property="volumeList">
								<option value="<bean:write name="volumeList" />" />
							</logic:iterate>
						</dataList>
						<br>
						「[数字]-[数字]」 を入力することで、その間にある複数巻を登録できます<br>
						　(例) 1-15　→ 1巻から15巻を登録</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="publisher" /></td>
				</tr>
				<tr>
					<td>出版社</td>
					<td>
						<input list="publisherList" id="publisher" name="publisher" autocomplete="off" value="<bean:write name="AddForm" property="publisher" />">
						<dataList id="publisherList">
							<logic:iterate id="publisherList" name="AddForm" property="publisherList">
								<option value="<bean:write name="publisherList" />" />
							</logic:iterate>
						</dataList>
						<html:hidden property="publisher" styleClass="publisher" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td><html:errors property="author" /></td>
				</tr>
				<tr>
					<td>著者</td>
					<td>
						<input list="authorList" id="author" name="author" autocomplete="off" value="<bean:write name="AddForm" property="author" />">
						<dataList id="authorList">
							<logic:iterate id="authorList" name="AddForm" property="authorList">
								<option value="<bean:write name="authorList" />" />
							</logic:iterate>
						</dataList>
						<html:hidden property="author" styleClass="author" />
					</td>
				</tr>
			</table>
			<div class="btnWrapper">
				<html:hidden property="selectBook" />
				<html:button property="toMain" value="戻る" />
				<html:submit property="submitBtn" value="登録" styleClass="rightBtn" />
			</div>
		</html:form>
	</div>

	<script type="text/javascript">
	$(function(){
		if($('[name="selectBook"]').val() == 'true'){
			$('input[name="title"]:not([type="hidden"])').prop('disabled',true);
			$('[name="publisher"]:not([type="hidden"])').prop('disabled',true);
			$('[name="author"]:not([type="hidden"])').prop('disabled',true);
		}else{
			$('input[name="title"][type="hidden"]').prop('disabled',true);
			$('[name="publisher"][type="hidden"]').prop('disabled',true);
			$('[name="author"][type="hidden"]').prop('disabled',true);
		}

		$('[name="toMain"]').on('click', function(){
			location.href = '<%= request.getContextPath() %>/view/main/main.jsp';
		});

		var noError = <%= request.getAttribute("noError") %>;
		var registComplete = <%= request.getAttribute("registComplete") %>;

		if(noError && !registComplete){
			var registMsg = '確認用ポップアップ';
			document.AddForm.action = '<%= request.getContextPath() %>/add.do?method=regist';
			if(window.confirm(registMsg)){
				//登録処理
				document.AddForm.submit();
				document.AddForm.action='<%= request.getContextPath() %>/add.do?method=validate';
			}else{
				//キャンセル
				document.AddForm.action='<%= request.getContextPath() %>/add.do?method=validate';
			}
		}
		if(noError && registComplete){
			alert('処理が終了しました。');
		}
	});
	</script>
</body>
</html>