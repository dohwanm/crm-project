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
			//하이드 숨기는 기능
			$("#pwdChk").hide();
			//첨부파일 이미지 보여주기 위한 속성 추가
			var file = "<c:out value='${detail.h_File}'/>";
			
			if(file !=""){
				$("#fileImage").attr({
					src:"/uploadStorage/${detail.h_File}",
					width:"450px",
					height:"200px"
				});
			}
			
			//수정 버튼 클릭시 처리
			$("#updateForm").click(function(){
				$("#pwdChk").show();
				$("#msg").text("작성시 입력한 비밀번호를 입력하세요").css("color","#000099");
				
				butChk= 1;
			});
			
			//삭제 버튼 클릭시 처리 
			$("#boardDelete").click(function(){
				$("#pwdChk").show();
				$("#msg").text("작성시 입력한 비밀번호를 입력하세요").css("color","#000099");
				
				butChk= 2;
			});
			
			//비밀번호 확인 버튼 클릭시 
			$("#pwdBut").click(function(){
				pwdConfirm();
			});
			
			//목록 버튼 클릭시 처리 
			$("#boardList").click(function(){
				location.href="/board/boardList.htk";
			});
			
		});
		
		
		//비밀번호 확인 버튼 클릭시 실질적인 처리 
		function pwdConfirm(){
			if(!chkSubmit($("#h_Pwd"),"비밀번호를")) return;
			else{
				$.ajax({
					url:"/board/pwdConfirm.htk",
					type:"post",
					data:$("#f_pwd").serialize(),
					error:function(){
						alert("시스템 오류입니다 ");
					},
					success:function(resultData){
						var goUrl = "";
						if(resultData == 0){
							$("#msg").text("작성시 입력한 비밀번호가 일치 하지 않습니다").css("color","red");
							$("#h_Pwd").select();
						}else if(resultData ==1){ //일치할 경우
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
		<div id="boardTit"><h3>글상세</h3></div>
		<form name="f_data" id="f_data" method="post">
			<input type="hidden" name="h_Num" id="h_Num" value="${detail.h_Num}"/>
			<input type="hidden" name="h_File" id="h_File" value="${detail.h_File}"/>
		</form>
		<%--비밀번호 확인 버튼 및 버튼 추가 --%>
		
		<table id="boardPwdBut">
			<tr>
				<td id="btd1">
					<div id="pwdChk">
						<form name="f_pwd" id="f_pwd">
							<input type="hidden" name="h_Num" id="h_Num" value="${detail.h_Num}">
							<label for="h_Pwd" id="l_pwd">비밀번호 : </label>
							<input type="password" name="h_Pwd" id="h_Pwd">
							<input type="button" id="pwdBut" value="확인">
							<span id="msg"></span>
						</form>
					</div>
				</td>
				<td id="btd2">
					<input type="button" value="수정" id="updateForm">
					<input type="button" value="삭제" id="boardDelete">
					<input type="button" value="목록" id="boardList">
				</td>
			</tr>
		</table>
		<%-- 비밀번호 확인 버튼 및 버튼 추가 종료 --%>
		
		<%-- 상세정보 보여주기 시작 --%>
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
						<td class="ac">작성자</td>
						<td>${detail.h_Name}</td>
						<td class="ac">작성일</td>
						<td>${detail.h_Insertdate}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td>${detail.h_Title}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">내용</td>
						<td colspan="3">${detail.h_Content}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">첨부파일 파일</td>
						<td colspan="3"><img id="fileImage"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<%--상세 정보 보여주기 종료 --%>
		
		 <jsp:include page="reply.jsp"></jsp:include>
	</body>
</html>