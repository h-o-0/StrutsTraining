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
		<html:form action="/detail" styleId="stockListForm">
		<h3><bean:write name="DetailForm" property="library.title"/></h3>
		<div id="libraryDetailWrapper">
			<table id="libraryDetail">
				<tr>
					<td>出版社</td>
					<td><bean:write name="DetailForm" property="library.publisher"/></td>
				</tr>
				<tr>
					<td>著者</td>
					<td><bean:write name="DetailForm" property="library.author"/></td>
				</tr>
			</table>
		</div>
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
							<td class="noStock" id='<bean:write name="stockList" property="id"/>'>
						</logic:equal>
						<logic:notEqual name="stockList" property="status" value="0">
							<td class="stock" id='<bean:write name="stockList" property="id"/>'>
						</logic:notEqual>

							<span class="volume"><bean:write name="stockList" property="volume"/></span>

							<logic:notEqual name="stockList" property="loan_comment" value="">
								<span class="tooltip"><bean:write name="stockList" property="loan_comment"/></span>
							</logic:notEqual>
						</td>

					<logic:equal name="index" value="<%= String.valueOf(endNum) %>">
						</tr>
					</logic:equal>

				</logic:iterate>
			</table>
		</div>
		</html:form>
		<div class="btnWrapper">
			<html:button property="toMain" value="戻る" />
			<div class="rightBtn">
				<html:form action="/detail" styleId="deleteForm" styleClass="adminOnly">
					<html:hidden property="selectList"/>
					<html:hidden property="id"/>
					<html:submit property="deleteBtn" value="削除" />
				</html:form>
				<html:form action="/add" styleId="addForm" styleClass="adminOnly">
					<html:hidden name="DetailForm" property="id"/>
					<html:hidden property="volume"/>
					<html:submit property="addBtn" value="追加" />
				</html:form>
				<html:form action="/detail" styleId="lendForm">
					<html:hidden property="selectList"/>
					<html:hidden property="id"/>
					<html:hidden property="loan_comment"/>
					<html:submit property="lendBtn" value="貸出/返却" />
					</html:form>
			</div>
		</div>

	</div>

	<script type="text/javascript">
	$(function(){
		var userId = '<%= session.getAttribute("userId") %>';
		var deleteCheck = <%= request.getAttribute("deleteCheck") %>;
		var lendCheck = <%= request.getAttribute("lendCheck") %>;
		var registComplete = <%= request.getAttribute("registComplete") %>;
		var status = <%= request.getAttribute("status") %>;

		//初期表示：選択中のものをグレーにする
		getSelectList();

		//adminのみ表示
		if(userId == 'admin'){
			$('.adminOnly').addClass('show');
		}

		$('#stockList td').click(function(){
			if($(this).hasClass('selected')){
				$(this).removeClass('selected');
			}else{
				$(this).addClass('selected');
			}
		});

		$('[name="toMain"]').on('click', function(){
			location.href = '<%= request.getContextPath() %>/view/main/main.jsp';
		});

		//削除
		$('[name="deleteBtn"]').click(function(){
			sendSelectList();
			$('#deleteForm').attr('action', '<%= request.getContextPath() %>/detail.do?method=deleteCheck');
			$('#deleteForm').submit();
		});

		//追加
		$('[name="addBtn"]').click(function(){
			var regex = new RegExp(/^[0-9]+$/);
			var lastVolume = $('#stockList td').last().find('.volume').text();
			if(regex.test(lastVolume)){
				$('[name="volume"]').val(parseInt(lastVolume) + 1);
			}
			$('#addForm').submit();
		});

		//貸出返却
		$('[name="lendBtn"]').click(function(){
			sendSelectList();
			$('#lendForm').attr('action', '<%= request.getContextPath() %>/detail.do?method=lendCheck');
			$('#lendForm').submit();
		});

		//削除ポップアップ
		if(deleteCheck){
			var status = '';

			var selectVolumeList = getSelectVolumeList();

			$('.selected').each(function(){
				if($(this).hasClass('noStock')){
					status = '貸出状態の巻が選択されています';
				}
			});

			var msg =
				'以下の巻数情報、貸出状況を削除します。\n'
				+ 'よろしいですか？\n'
				+ '\n'
				+ 'タイトル：' + $('h3').text() + '\n'
				+ '巻数：' + selectVolumeList + '巻\n'
				+ status;

			if(window.confirm(msg)){
				//登録処理
				$('#deleteForm').attr('action','<%= request.getContextPath() %>/detail.do?method=delete');
				$('#deleteForm').submit();
			}
		}

		//返却ポップアップ
		if(lendCheck){

			var selectVolumeList = getSelectVolumeList();

			if(status == '0'){
				//返却
				var msg =
					'以下を返却します。よろしいですか？\n'
					+ '\n'
					+ 'タイトル：' + $('h3').text() + '\n'
					+ '巻数：' + selectVolumeList + '巻';

				if(window.confirm(msg)){
					//登録処理
					$('#lendForm').attr('action','<%= request.getContextPath() %>/detail.do?method=lend');
					$('#lendForm').submit();
				}
			}else{
				//貸出
				var msg =
					'以下を貸出します。よろしいですか？\n'
					+ '\n'
					+ 'タイトル：' + $('h3').text() + '\n'
					+ '巻数：' + selectVolumeList + '巻\n'
					+ '\n'
					+ 'ツールチップに表示するコメントを入力してください\n';

				loan_comment = window.prompt(msg,"");

				if(loan_comment != null){
					//登録処理
					$('[name="loan_comment"]').val(loan_comment);
					$('#lendForm').attr('action','<%= request.getContextPath() %>/detail.do?method=lend');
					$('#lendForm').submit();
				}
			}
		}

		//処理完了ポップアップ
		if(registComplete){
			alert('処理が終了しました');
			location.href = '<%= request.getContextPath() %>/view/main/main.jsp';
		}

		function getSelectList() {
			var selectList = $('[name="selectList"]').val().split(',');
			if(selectList[0] == ''){
				return;
			}
			$.each(selectList,function(index,value){
				$('#stockList td#'+value).addClass('selected');
			});
		}

		function sendSelectList() {
			var selectList = new Array();
			$('#stockList td').each(function(){
				if($(this).hasClass('selected')){
					selectList.push($(this).attr('id'));
				}
			});
			selectList = selectList.join(',');
			$('[name="selectList"]').val(selectList);
		}

		function getSelectVolumeList() {
			var selectVolumeList = new Array();
			var selectIdList = $('[name="selectList"]').val().split(',');
			if(selectIdList[0] == ''){
				return '';
			}
			$.each(selectIdList,function(index,value){
				selectVolumeList.push($.trim($('#stockList td#'+value+' .volume').text()));
			});

			return selectVolumeList.join(',');
		}
	});
	</script>
</body>
</html>