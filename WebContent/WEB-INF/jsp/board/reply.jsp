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
			//�⺻ ��� ��� �ҷ�����
			
		$(function(){
			alert("����");
			var h_Num = "<c:out value='${detail.h_Num}'/>";
			listAll(h_Num)
			
			//��� ���� ���� �̺�Ʈ
			
			$("#replyInsert").click(function(){
				//�ۼ��� �̸��� ���� �Է¿��� �˻�
				alert("�ȴ��Ͽ�")
				if(!chkSubmit($("#r_name"),"�̸���")) return;
				else if(!chkSubmit($("#r_content"),"������")) return;
				else{
					var insertUrl = "/replies/replyInsert.htk";
					//�� ������ ���� Post ����� Ajax ���� ó��
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
							//����� ������ �߻� �� ���
							alert("�ý��� ����");
						},
						
						success : function(abc){
							if(abc == "SUCCESS"){
								alert("��� ����");
								dataReset();
								listAll(h_Num);
							}
						}
					});
				}
			});
				
			//���� ��ư Ŭ���� 
			//on �Լ� : �������� �߰��� ��ҿ� ���� �̺�Ʈ�� �̸� ������ ���� �Լ� 
			//���� �߰��� ��ҿ� �̺�Ʈ�̱� �빮�� �� �̺�Ʈ�� ���ǵǴ� ���������� ����� �������� ���� ���̱� ��ƹ���
			//�� �̺�Ʈ�� document��ü�� ���� �ؾ���
			//$(document).on("�̺�Ʈ��", ������)
			$(document).on("click",".update_form",function(){
				alert("������ư Ŭ��");
				$(".reset_btn").click();
				var conText = $(this).parents("li").children().eq(1).html();
				
				console.log("conText >> : "+ conText);
				
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html("");//�̰� ��� �ؿ� html���� ����� �˴ϴ� 
				
				var data = "<textarea name='content' id='content'>" + conText + "</textarea>";
								
				data += "&nbsp;&nbsp;<input type='button' class='update_btn' value='�����Ϸ�'>";
				data += "<input type='button' class='reset_btn' value='�������'>"; 
				conArea.html(data); 
				
			});
				
				
			//�ʱ�ȭ ��ư
			
			$(document).on("click", ".reset_btn", function(){
				alert("�ʱ�ȭ��ư Ŭ��");
				
				var conText = $(this).parents("li").find("textarea").html();
				
				$(this).parents("li").find("input[type='button']").show();
				
				var conArea = $(this).parents("li").children().eq(1);
				
				conArea.html(conText);
				
			});
			
			//�� ������ ���� Ajax ����ó��
			
			$(document).on("click",".update_btn", function(){
				alert("�� ������ ���� �����۽� !");
				var r_num = $(this).parents("li").attr("data-num");
				var r_content = $("#content").val();
				
				if(!chkSubmit($("#content"),"��� ������")) return;
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
								alert("���� ����");
								listAll(h_Num);
							}
						}
					});
				}
				
			});
			
			
			//�� ������ ���� Ajax ����ó��
			
			$(document).on("click",".delete_btn", function(){
				alert("�� ������ ���� �����۽� !");
				var r_num = $(this).parents("li").attr("data-num");
				console.log("r_num >> : "+ r_num);
				
				if(confirm("�����Ͻ� ��� ���� �Ұž� ? ")){
					
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
								alert("���� ����");
								listAll(h_Num);
							}
						}
					});	
				}
					
			});
			
		});
				
				
		//����Ʈ ��û �Լ�
		function listAll(h_Num){
			$("#comment_list").html("");
			
			var url = "/replies/all/" + h_Num+".htk";
			
			$.getJSON(url, function(data){
				console.log("������ ���� >> : "+ data.length);
				
				$(data).each(function(){
					var r_num = this.r_num;
					var r_name = this.r_name;
					var r_content = this.r_content;
					var r_insertdate = this.r_insertdate;
					addNewItem(r_num, r_name, r_content, r_insertdate);
				});
			}).fail(function(){
				alert("��� ��� �ҷ����µ� ���� ");
			});
		}
		
		//���ο� ���� ȭ�餷 ���߰��ϱ� ���� �Լ�
		function addNewItem(r_num,r_name,r_content,r_insertdate){
			//���ο� ���� �߰��� li�±� ��ü
			var new_li = $("<li>");
			new_li.attr("data-num", r_num);
			new_li.addClass("comment_item");
			
			//�ۼ��� ������ ������ <p>�±�
			var writer_p = $("<p>");
			writer_p.addClass("writer");
			
			//�ۼ��� ������ �̸�
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html(r_name + "��");
			
			//�ۼ��Ͻ�
			var date_span = $("<span>");
			date_span.html("/"+ r_insertdate +" ");
			
			//�����ϱ� ��ư
			var up_input = $("<input>");
			up_input.attr({"type" : "button" ,"value" : "�����ϱ�"});
			up_input.addClass("update_form");
			
			//�����ϱ� ��ư
			var del_input = $("<input>");
			del_input.attr({"type" : "button" ,"value" :"�����ϱ�"});
			del_input.addClass("delete_btn");
			
			//����
			var content_p = $("<p>");
			content_p.addClass("con");
			content_p.html(r_content);
			
			//����
			writer_p.append(name_span).append(date_span).append(up_input).append(del_input)
			new_li.append(writer_p).append(content_p);
			$("#comment_list").append(new_li);
		}
		//input �±׵鿡 ���� �ʱ�ȭ �Լ�
		
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
						<label for="r_name">�ۼ���</label>
						<input type="text" name="r_name" id="r_name">
						<label for="r_name">��й�ȣ</label>
						<input type="password" name="r_pwd" id="r_pwd">
						<input type="button" id="replyInsert" value="�����ϱ�">
					</div>
					<div>
						<label for="r_content">��� ����</label>
						<textarea name="r_content" id="r_content"></textarea>
					</div>
				</form>
			</div>
			<ol id="comment_list">
				<!-- ���⿡ ���� ���� ��Ұ� �� -->
			</ol>
		</div>
	</body>
</html>