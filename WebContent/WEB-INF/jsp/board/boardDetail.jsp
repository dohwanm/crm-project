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
		<title>Insert dddd here</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="/include/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript">
		var butChk= 0;
		$(function(){
			//���̵� ����� ���
			$("#pwdChk").hide();
			//÷������ �̹��� �����ֱ� ���� �Ӽ� �߰�
			var file = "<c:out value='${detail.h_File}'/>";
			
			if(file !=""){
				$("#fileImage").attr({
					src:"/uploadStorage/${detail.h_File}",
					width:"450px",
					height:"200px"
				});
			}
			
			//���� ��ư Ŭ���� ó��
			$("#updateForm").click(function(){
				$("#pwdChk").show();
				$("#msg").text("�ۼ��� �Է��� ��й�ȣ�� �Է��ϼ���").css("color","#000099");
				
				butChk= 1;
			});
			
			//���� ��ư Ŭ���� ó�� 
			$("#boardDelete").click(function(){
				$("#pwdChk").show();
				$("#msg").text("�ۼ��� �Է��� ��й�ȣ�� �Է��ϼ���").css("color","#000099");
				
				butChk= 2;
			});
			
			//��й�ȣ Ȯ�� ��ư Ŭ���� 
			$("#pwdBut").click(function(){
				pwdConfirm();
			});
			
			//��� ��ư Ŭ���� ó�� 
			$("#boardList").click(function(){
				location.href="/board/boardList.htk";
			});
			
		});
		
		
		//��й�ȣ Ȯ�� ��ư Ŭ���� �������� ó�� 
		function pwdConfirm(){
			if(!chkSubmit($("#h_Pwd"),"��й�ȣ��")) return;
			else{
				$.ajax({
					url:"/board/pwdConfirm.htk",
					type:"post",
					data:$("#f_pwd").serialize(),
					error:function(){
						alert("�ý��� �����Դϴ� ");
					},
					success:function(resultData){
						var goUrl = "";
						if(resultData == 0){
							$("#msg").text("�ۼ��� �Է��� ��й�ȣ�� ��ġ ���� �ʽ��ϴ�").css("color","red");
							$("#h_Pwd").select();
						}else if(resultData ==1){ //��ġ�� ���
							$("#msg").text("");
							if(butChk == 1){
								goUrl="/board/updateForm.htk";
							}else if(butChk ==2){
								goUrl="/board/boardDelete.htk";
							}
							$("#f_data").attr("action",goUrl);
							$("#f_data").submit();
						
						}
					}
				});
			}
		}
		
		</script>
	</head>
	<body>
		<div id="boardTit"><h3>�ۻ�</h3></div>
		<form name="f_data" id="f_data" method="post">
			<input type="hidden" name="h_Num" id="h_Num" value="${detail.h_Num}"/>
			<input type="hidden" name="h_File" id="h_File" value="${detail.h_File}"/>
		</form>
		<%--��й�ȣ Ȯ�� ��ư �� ��ư �߰� --%>
		
		<table id="boardPwdBut">
			<tr>
				<td id="btd1">
					<div id="pwdChk">
						<form name="f_pwd" id="f_pwd">
							<input type="hidden" name="h_Num" id="h_Num" value="${detail.h_Num}">
							<label for="h_Pwd" id="l_pwd">��й�ȣ : </label>
							<input type="password" name="h_Pwd" id="h_Pwd">
							<input type="button" id="pwdBut" value="Ȯ��">
							<span id="msg"></span>
						</form>
					</div>
				</td>
				<td id="btd2">
					<input type="button" value="����" id="updateForm">
					<input type="button" value="����" id="boardDelete">
					<input type="button" value="���" id="boardList">
				</td>
			</tr>
		</table>
		<%-- ��й�ȣ Ȯ�� ��ư �� ��ư �߰� ���� --%>
		
		<%-- ������ �����ֱ� ���� --%>
		<div id="boardDetail">
			<table>
				<colgroup>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
				</colgroup>
				<tbody>
					<tr>
						<td class="ac">�ۼ���</td>
						<td>${detail.h_Name}</td>
						<td class="ac">�ۼ���</td>
						<td>${detail.h_Insertdate}</td>
					</tr>
					<tr>
						<td class="ac">����</td>
						<td>${detail.h_Title}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">����</td>
						<td colspan="3">${detail.h_Content}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">÷������ ����</td>
						<td colspan="3"><img id="fileImage"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<%--�� ���� �����ֱ� ���� --%>
		
		 <jsp:include page="reply.jsp"></jsp:include>
	</body>
</html>