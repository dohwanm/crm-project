<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
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
				
				// ‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
				function submitContents(elClickedObj) {
				 // 에디터의 내용이 textarea에 적용된다.
				 oEditors.getById["h_Content"].exec("UPDATE_CONTENTS_FIELD", []);

				 // 에디터의 내용에 대한 값 검증은 이곳에서
				 // document.getElementById("ir1").value를 이용해서 처리한다.

				 try {
				     elClickedObj.form.submit();
				 } catch(e) {}
				
				}
	
				
				
				$("#boardInsert").click(function(){
					
					 oEditors.getById["h_Content"].exec("UPDATE_CONTENTS_FIELD", []);
					 try {
					     elClickedObj.form.submit();
					 } catch(e) {} 
					
					//입력값 체크
					alert("안뇽 클레오 파트라 ");
					if(!chkSubmit($("#h_Name"),"이름을")) return;
					else if(!chkSubmit($("#h_Title"),"제목을")) return;
					else if(!chkSubmit($("#h_Content"),"작성할 내용을")) return;
					else if(!chkSubmit($("#file"),"첨부 파일을")) return;
					else if(!chkSubmit($("#h_Pwd"),"비밀번호를")) return;
					else{
						//배열내의 값을 찾아서 인덱스를 반환 (요소가 없을경우 -1 반환)
						//jQuery.inArray(찾을 값, 검색대상의 배열)
						
						var ext = $("#file").val().split('.').pop().toLowerCase();
						if(jQuery.inArray(ext,['gif','png','jpg','jpeg']) == -1){
							alert('gif,png,jpg,jpeg 파일만 업로드 가능합니다');
							return;
						}
						$("#f_writeForm").attr({
							"method":"POST",
							"action":"/board/boardInsert.htk"
						});
						$("#f_writeForm").submit();
					}
				});
					
				// 목록 버튼 클릭시 처리 
				$("#boardList").click(function(){
					location.href="/board/boardList.htk";
				});
					
			});
			
			
		</script>
		
		
	</head>
	<body>
		<div id="boardTit"><h3>글쓰기</h3></div>
		<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
			<table id="boardWrite">
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="h_Name" id="h_Name">
				</td>
			</tr>
			<tr>
				<td>글제목</td>
				<td>
					<input type="text" name="h_Title" id="h_Title">
				</td>
			
			</tr>
			<tr>
				<td>내용</td>
				<td height="200">
					<textarea rows="10" cols="70" name="h_Content" id="h_Content"></textarea>
				</td>
			</tr>	
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="file" id="file">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="h_Pwd" id="h_Pwd">
				</td>
			</tr>
			</table>
		</form>
		<div id="boardBut">
			<input type="button" value="저장" class="but" id="boardInsert">
			<input type="button" value="목록" class="but" id="boardList">
		</div>
	</body>
</html>