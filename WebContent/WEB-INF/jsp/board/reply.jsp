<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/include/css/reply.css"/>
		<script type="text/javascript" src="/include/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript">
			//기본 댓글 목록 불러오기
			
		$(function(){
			alert("ㅎㅇ");
			var h_Num = "<c:out value='${detail.h_Num}'/>";
			listAll(h_Num)
			
			//댓글 내용 저장 이벤트
			
			$("#replyInsert").click(function(){
				//작성자 이름에 대한 입력여부 검사
				alert("안뇽하욤")
				if(!chkSubmit($("#r_name"),"이름을")) return;
				else if(!chkSubmit($("#r_content"),"내용을")) return;
				else{
					var insertUrl = "/replies/replyInsert.htk";
					//글 저장을 위한 Post 방식의 Ajax 연동 처리
					$.ajax({
						url: insertUrl,
						type: "post",
						headers : {
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"POST"
						},
						dataType:"text",
						data : JSON.stringify({
							h_Num:h_Num,
							r_name:$("#r_name").val(),
							r_pwd:$("#r_pwd").val(),
							r_content:$("#r_content").val(),
						}),
						
						error : function(){
							//실행시 오류가 발생 할 경우
							alert("시스템 오류");
						},
						
						success : function(abc){
							if(abc == "SUCCESS"){
								alert("댓글 성공");
								dataReset();
								listAll(h_Num);
							}
						}
					});
				}
			});
				
			//수정 버튼 클릭시 
			//on 함수 : 동적으로 추가할 요소에 대한 이벤트를 미리 정의해 놓는 함수 
			//새로 추가될 요소에 이벤트이기 대문에 이 이벤트가 정의되는 시점에서는 대상이 존재하지 않을 것이기 대ㅖ문에
			//이 이벤트는 document객체에 설정 해야함
			//$(document).on("이벤트명", 셀렉터)
			$(document).on("click",".update_form",function(){
				alert("수정버튼 클릭");
				$(".reset_btn").click();
				var conText = $(this).parents("li").children().eq(1).html();
				
				console.log("conText >> : "+ conText);
				
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");//이거 없어도 밑에 html에서 덮어쓰기 됩니당 
				
				var data = "<textarea name='content' id='content'>" + conText + "</textarea>";
								
				data += "&nbsp;&nbsp;<input type='button' class='update_btn' value='수정완료'>";
				data += "<input type='button' class='reset_btn' value='수정취소'>"; 
				conArea.html(data); 
				
			});
				
				
			//초기화 버튼
			
			$(document).on("click", ".reset_btn", function(){
				alert("초기화버튼 클릭");
				
				var conText = $(this).parents("li").find("textarea").html();
				
				$(this).parents("li").find("input[type='button']").show();
				
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html(conText);
				
			});
			
			//글 수정을 위한 Ajax 연동처리
			
			$(document).on("click",".update_btn", function(){
				alert("글 수정을 위한 에이작스 !");
				var r_num = $(this).parents("li").attr("data-num");
				var r_content = $("#content").val();
				
				if(!chkSubmit($("#content"),"댓글 내용을")) return;
				else{
					$.ajax({
						url:'/replies/'+r_num+".htk",
						type: 'put',
						headers: {
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"POST"
						},
						data:JSON.stringify({
							r_content:r_content
						}),
						dataType: 'text',
						success:function(result){
							console.log("result > : " + result);
							if(result == "SUCCESS"){
								alert("수정 성공");
								listAll(h_Num);
							}
						}
					});
				}
				
			});
			
			
			//글 삭제를 위한 Ajax 연동처리
			
			$(document).on("click",".delete_btn", function(){
				alert("글 삭제를 위한 에이작스 !");
				var r_num = $(this).parents("li").attr("data-num");
				console.log("r_num >> : "+ r_num);
				
				if(confirm("선택하신 댓글 삭제 할거야 ? ")){
					
					$.ajax({
						url:'/replies/'+r_num+".htk",
						type: 'delete',
						headers: {
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"POST"
						},
						dataType: 'text',
						success:function(result){
							console.log("result > : " + result);
							if(result == "SUCCESS"){
								alert("삭제 성공");
								listAll(h_Num);
							}
						}
					});	
				}
					
			});
			
		});
				
				
		//리스트 요청 함수
		function listAll(h_Num){
			$("#comment_list").html("");
			
			var url = "/replies/all/" + h_Num+".htk";
			
			$.getJSON(url, function(data){
				console.log("데이터 길이 >> : "+ data.length);
				
				$(data).each(function(){
					var r_num = this.r_num;
					var r_name = this.r_name;
					var r_content = this.r_content;
					var r_insertdate = this.r_insertdate;
					addNewItem(r_num, r_name, r_content, r_insertdate);
				});
			}).fail(function(){
				alert("댓글 목록 불러오는데 실패 ");
			});
		}
		
		//새로운 글을 화면ㅇ ㅔ추가하기 위한 함수
		function addNewItem(r_num,r_name,r_content,r_insertdate){
			//새로운 글이 추가될 li태그 객체
			var new_li = $("<li>");
			new_li.attr("data-num", r_num);
			new_li.addClass("comment_item");
			
			//작성자 정보가 지정될 <p>태그
			var writer_p = $("<p>");
			writer_p.addClass("writer");
			
			//작성자 정보의 이름
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html(r_name + "님");
			
			//작성일시
			var date_span = $("<span>");
			date_span.html("/"+ r_insertdate +" ");
			
			//수정하기 버튼
			var up_input = $("<input>");
			up_input.attr({"type" : "button" ,"value" : "수정하기"});
			up_input.addClass("update_form");
			
			//삭제하기 버튼
			var del_input = $("<input>");
			del_input.attr({"type" : "button" ,"value" :"삭제하기"});
			del_input.addClass("delete_btn");
			
			//내용
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(r_content);
			
			//조립
			writer_p.append(name_span).append(date_span).append(up_input).append(del_input)
			new_li.append(writer_p).append(content_p);
			$("#comment_list").append(new_li);
		}
		//input 태그들에 대한 초기화 함수
		
		function dataReset(){
			$("#r_name").val("");
			$("#r_pwd").val("");
			$("#r_content").val("");
		}
		</script>
	</head>
	<body>
		<div id="replyContainer">
			<h1></h1>
			<div id="comment_write">
				<form id="comment_form">
					<div>
						<label for="r_name">작성자</label>
						<input type="text" name="r_name" id="r_name">
						<label for="r_name">비밀번호</label>
						<input type="password" name="r_pwd" id="r_pwd">
						<input type="button" id="replyInsert" value="저장하기">
					</div>
					<div>
						<label for="r_content">댓글 내용</label>
						<textarea name="r_content" id="r_content"></textarea>
					</div>
				</form>
			</div>
			<ol id="comment_list">
				<!-- 여기에 동적 생성 요소가 들어감 -->
			</ol>
		</div>
	</body>
</html>