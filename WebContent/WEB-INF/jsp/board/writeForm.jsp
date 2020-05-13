<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
    String ctx = request.getContextPath();    //���ؽ�Ʈ�� ������.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert dd here</title>
		
		<script type="text/javascript" src="/smartEditor/js/service/HuskyEZCreator.js" charset="EUC-KR"></script>
		
		
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript"  src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		
		
		
		
		<script type="text/javascript">
		
		
		
			$(function(){	
				
				var oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
				 oAppRef: oEditors,
				 elPlaceHolder: "h_Content",
				 sSkinURI: "/smartEditor/SmartEditor2Skin.html",
				 fCreator: "createSEditor2"
				 
				});
				
				// �����塯 ��ư�� ������ �� ������ ���� �׼��� ���� �� submitContents�� ȣ��ȴٰ� �����Ѵ�.
				function submitContents(elClickedObj) {
				 // �������� ������ textarea�� ����ȴ�.
				 oEditors.getById["h_Content"].exec("UPDATE_CONTENTS_FIELD", []);

				 // �������� ���뿡 ���� �� ������ �̰�����
				 // document.getElementById("ir1").value�� �̿��ؼ� ó���Ѵ�.

				 try {
				     elClickedObj.form.submit();
				 } catch(e) {}
				
				}
	
				
				
				$("#boardInsert").click(function(){
					
					 oEditors.getById["h_Content"].exec("UPDATE_CONTENTS_FIELD", []);
					 try {
					     elClickedObj.form.submit();
					 } catch(e) {} 
					
					//�Է°� üũ
					alert("�ȴ� Ŭ���� ��Ʈ�� ");
					if(!chkSubmit($("#h_Name"),"�̸���")) return;
					else if(!chkSubmit($("#h_Title"),"������")) return;
					else if(!chkSubmit($("#h_Content"),"�ۼ��� ������")) return;
					else if(!chkSubmit($("#file"),"÷�� ������")) return;
					else if(!chkSubmit($("#h_Pwd"),"��й�ȣ��")) return;
					else{
						//�迭���� ���� ã�Ƽ� �ε����� ��ȯ (��Ұ� ������� -1 ��ȯ)
						//jQuery.inArray(ã�� ��, �˻������ �迭)
						
						var ext = $("#file").val().split('.').pop().toLowerCase();
						if(jQuery.inArray(ext,['gif','png','jpg','jpeg']) == -1){
							alert('gif,png,jpg,jpeg ���ϸ� ���ε� �����մϴ�');
							return;
						}
						$("#f_writeForm").attr({
							"method":"POST",
							"action":"/board/boardInsert.htk"
						});
						$("#f_writeForm").submit();
					}
				});
					
				// ��� ��ư Ŭ���� ó�� 
				$("#boardList").click(function(){
					location.href="/board/boardList.htk";
				});
					
			});
			
			
		</script>
		
		
	</head>
	<body>
		<div id="boardTit"><h3>�۾���</h3></div>
		<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
			<table id="boardWrite">
			<tr>
				<td>�ۼ���</td>
				<td>
					<input type="text" name="h_Name" id="h_Name">
				</td>
			</tr>
			<tr>
				<td>������</td>
				<td>
					<input type="text" name="h_Title" id="h_Title">
				</td>
			
			</tr>
			<tr>
				<td>����</td>
				<td height="200">
					<textarea rows="10" cols="70" name="h_Content" id="h_Content"></textarea>
				</td>
			</tr>	
			<tr>
				<td>÷������</td>
				<td>
					<input type="file" name="file" id="file">
				</td>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td>
					<input type="password" name="h_Pwd" id="h_Pwd">
				</td>
			</tr>
			</table>
		</form>
		<div id="boardBut">
			<input type="button" value="����" class="but" id="boardInsert">
			<input type="button" value="���" class="but" id="boardList">
		</div>
	</body>
</html>