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
		<html:form action="/search">
		<div id="searchFormWrapper">
			<html:select property="searchCategory" value="title">
				<html:option value="title">タイトル</html:option>
				<html:option value="publisher">出版社</html:option>
				<html:option value="author">著者</html:option>
			</html:select>

			<html:text property="searchWord"/>
			<html:submit property="searchBtn" value="検索" />
		</div>
		<div id="searchResultWrapper">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>タイトル</th>
						<th>出版社</th>
						<th>著者</th>
						<th>貸出状態</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="libraryList" name="SearchForm" property="libraryList">
						<tr>
							<td align="center"><html:checkbox property="selectBook"/></td>
							<td class="title"><bean:write name="libraryList" property="title"/></td>
							<td class="publisher"><bean:write name="libraryList" property="publisher"/></td>
							<td class="author"><bean:write name="libraryList" property="author"/></td>
							<td align="center"><html:link action="/detail">貸出状態</html:link></td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		</html:form>
		<div class="btnWrapper">
			<html:form action="/add">
				<html:hidden property="title" />
				<html:hidden property="volume" />
				<html:hidden property="publisher" />
				<html:hidden property="author" />
				<html:hidden property="selectBook" />
				<html:submit property="addBtn" value="追加" styleClass="rightBtn" />
			</html:form>
		</div>
	</div>

	<script type="text/javascript">
	$(function(){
		//チェックボックスを択一にする
		$('#searchResultWrapper').find('[name="selectBook"]').click(function(){
			if($(this).prop('checked')){
				$('[name="selectBook"]').prop('checked',false);
				$(this).prop('checked',true);
			}
		});

		$('[name="addBtn"]').click(function(){
			for(var i=0; i<document.SearchForm.selectBook.length; i++){
				if(document.SearchForm.selectBook[i].checked){
					$('[name="title"]').val($('td.title:eq('+i+')').text());
					$('[name="volume"]').val('1');
					$('[name="publisher"]').val($('.publisher:eq('+i+')').text());
					$('[name="author"]').val($('.author:eq('+i+')').text());
					$('[name="selectBook"][type="hidden"]').val('true');
					break;
				}
			}
		});
	});
	</script>
</body>
</html>