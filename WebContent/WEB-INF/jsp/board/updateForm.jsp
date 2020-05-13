<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script>
			$(document).ready(function(){
				alert("gd");
				//������ư Ŭ���� ó��
				$("#boardUpdate").click(function(){
					//�Է°� üũ
					if(!chkSubmit($("#h_Name"),"�̸���")) return;
					else if(!chkSubmit($("#h_Title"),"������")) return;
					else if(!chkSubmit($("#h_Content"),"�ۼ��� ������")) return;
					else{
						if($("#file").val().indexOf(".")> -1){
							var ext = $("#file").val().split('.').pop().toLowerCase();
							if(jQuery.inArray(ext,['gif','png','jpg','jpeg']) == -1){
								alert("gif, png, jpg, jpeg ���ϸ� ���ε� �� �� �ֽ��ϴ�");
								return;
							}
						}
						//console.log("�⺻ ���ϸ� : "+"$("#h_File").val());
						$("#f_writeForm").attr({
							"method":"POST",
							"action":"/board/boardUpdate.htk"
						});
						$("#f_writeForm").submit();
					}
					
				});
				
				//��� ��ư Ŭ���� 
				$("#boardList").click(function(){
					location.href="/board/boardList.htk";
				});
			});
		</script>
	</head>
	<body>
		<div id="boardTit"><h3>�ۼ���</h3></div>
		<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
			<input type="hidden" id="h_Num" name="h_Num" value="${updateData.h_Num}">
			<input type="hidden" id="h_File" name="h_File" value="${updateData.h_File}">
			<table id="boardWrite">
				<tr>
					<td>�ۼ���</td>
					<td>
						<input type="text" name="h_Name" id="h_Name" value="${updateData.h_Name}">
					</td>
				</tr>
				<tr>
					<td>������</td>
					<td>
						<input type="text" name="h_Title" id="h_Title" value="${updateData.h_Title}">
					</td>
				</tr>
				<tr>
				<td>����</td>
				<td height="200">
					<textarea rows="10" cols="70" name="h_Content" id="h_Content">${updateData.h_Content}</textarea>
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
						<label>������ ��й�ȣ�� �Է����ּ���</label>
				</td>
			</tr>	
			</table>
		</form>
		<div id="boardBut">
			<input type="button" value="����" class="but" id="boardUpdate">
			<input type="button" value="���" class="but" id="boardList">
		</div>
	</body>
</html>