<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld" %>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert sdsdsdsdsdsdsdsdsdsdsds here</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="/include/js/jquery-1.11.0.min.js">
		</script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript">
			$(function(){		
				alert("${param.page}");
				//�˻� �� �˻� ���� �˻� �ܾ� ���
				if("<c:out value='${data.keyword}'/>"!=""){
					$("#keyword").val("<c:out value='${data.keyword}'/>");
					$("#search").val("<c:out value='${data.search}'/>");
				}
				
				//�� �������� ������ ���ڵ� �� ��ȸ �� ������ �� �״�� �����ϱ� ���� ����
				if("<c:out value='${data.pageSize}'/>"!=""){
					$("#pageSize").val("<c:out value='${data.pageSize}'/>");
				}
			
				
				//�˻� ����� ����� ó��
				$("#search").change(function(){
					if($("#search").val()=="all"){
						$("#keyword").val("��ü ������ ��ȸ�մϴ�");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}
				});
				
				//�˻� ��ư Ŭ����
				$("#searchData").click(function(){			
					if($("#srarch").val()!="all"){
						if(!chkSubmit($("#keyword"),"�˻��")) return;
					}
					goPage(1);
				});
				
				//�� �������� ������ ���ڵ� �� ����� �븶�� ó�� 
				$("#pageSize").change(function(){
					goPage(1);
				});
				
				//�۾��� ��ư Ŭ�� �� ó�� �̺�Ʈ
				$("#writeForm").click(function(){
					location.href="/board/writeForm.htk";
				});
				
				//���� Ŭ���� �� ������ �̵�
				$(".goDetail").click(function(){
					var h_Num = $(this).parents("tr").attr("data-num");
					$("#h_Num").val(h_Num);
					//�� �������� �̵��ϱ� ���� form �߰� (id : detailForm)
					$("#detailForm").attr({
						"method":"get",
						"action":"/board/boardDetail.htk"
					});
					$("#detailForm").submit();
				});
				
			});
			//���� ��ư Ŭ�� �� ó�� �Լ�
			function setOrder(order_by){
				$("#order_by").val(order_by);
				if($("#order_sc").val()=="DESC"){
					$("#order_sc").val("ASC");
				}else{
					$("#order_sc").val("DESC");
				}
				
				goPage(1);
			}
			
			//�˻��� �� �������� ������ ���ڵ� �� ó�� �� ����¡�� ó��
			
			function goPage(page){
				var s = page;
				if($("#search").val()=="all"){
					$("#keyword").val("");
				}
				$("#page").val(s);
				$("#f_search").attr({
					"method":"get",
					"action":"/board/boardList.htk"
				});
				$("#f_search").submit();
			}
			
		</script>
	</head>
	<body>
		<div id="boardContainer">
			<div id="boardTit"><h3>�۸��</h3></div>
			<form name="detailForm" id="detailForm">
				<input type="hidden" name="h_Num" id="h_Num">
				<input type="hidden" name="page"  value="${data.page}">
				<input type="hidden" name="pageSize" value="${data.pageSize}">
			</form>
			
			<%-- �˻� ��� ���� --%>
			
			<div id="boardSearch">
				<form id="f_search" name="f_search">
					<input type="hidden" name="page" id="page" value="${data.page}">
					<input type="hidden" id="order_by" name="order_by" value="${data.order_by}">
					<input type="hidden" id="order_sc" name="order_sc" value="${data.order_sc}">
					
						<table summary="�˻�">
							<colgroup>
								<col width="70%"></col>
								<col width="30%"></col>
							</colgroup>
							<tr>
								<td id="btd1">
									<label>�˻� ����</label>
									<select id="search" name="search">
										<option value="all">��ü</option>
										<option value="h_Title">����</option>
										<option value="h_Content">����</option>
										<option value="h_Name">�ۼ���</option>
									</select>
									<input type="text" name="keyword" id="keyword" placeholder="�˻�� �Է��ϼ���">
									<input type="button" value="�˻�" id="searchData">
								</td>
								<td id="btd2">�� ��������
									<select id="pageSize" name="pageSize">
										<option value="1">10��</option>
										<option value="2">20��</option>
										<option value="3">30��</option>
										<option value="40">40��</option>
										<option value="50">50��</option>
										<option value="60">60��</option>
										<option value="70">70��</option>
									</select>
								</td>
							</tr>
						</table>
				</form>
			</div>
			<%--�˻� ��� ���� --%>
			<%-- ����Ʈ ���� --%>
			<div id="boardList">
			
				<table summary="�Խ��� ����Ʈ">
				
					<colgroup>
					
						<col width="10%"/>
						<col width="62%"/>
						<col width="15%"/>
						<col width="13%"/>
					</colgroup>
					
					<thead>
					
					<tr>
						<th>
							<a href="javascript:setOrder('h_Num');">�۹�ȣ
								<c:choose>
									<c:when test="${data.order_by=='h_Num'and data.order_sc=='ASC'}">��</c:when>
									<c:when test="${data.order_by=='h_Num'and data.order_sc=='DESC'}">��</c:when>
									<c:otherwise>��</c:otherwise>
								</c:choose>
							</a>
						</th>
						<th>������</th>
						<th>
							<a href="javascript:setOrder('h_Insertdate');">�ۼ���
								<c:choose>
									<c:when test="${data.order_by=='h_Insertdate'and data.order_sc=='ASC'}">��</c:when>
									<c:when test="${data.order_by=='h_Insertdate'and data.order_sc=='DESC'}">��</c:when>
									<c:otherwise>��</c:otherwise>
								</c:choose>
							</a>
						</th>
						
						<th class="borcle">�ۼ���</th>
					</tr>
					
					</thead>
					<tbody>
					<!-- ������ ��� -->
					
					<c:choose>
						<c:when test="${not empty boardList}">
							<c:forEach var="board" items="${boardList}" varStatus="status">
								<tr align="center" data-num="${board.h_Num}">
									<td>${board.h_Num}</td> 
									<%-- <td>${count - (status.count-1)}</td> --%>
									<td align="left">
										<span class="goDetail">${board.h_Title}</span>
									</td>
									<td>${board.h_Insertdate}</td>
									<td>${board.h_Name}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" align="center">��ϵ� �Խù��� �������� �ʽ��ϴ�</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					</tbody>
				</table>
			</div>
			<%-- ����Ʈ ���� --%>
			
			<%-- �۾��� ��ư ��� ����--%>
			<div id="boardBut">
				<input type="button" value="�۾���" id="writeForm">
			</div>
			<%-- �۾��� ��ư ��� ���� --%>
			
			
			
			<%-- ������ �׺���̼� ����--%>
			
			<div id="boardPage">
				<tag:paging page ="${param.page}" total="${total}" list_size="${data.pageSize}" />
			</div>
			
			<%-- ������ �׺���̼� ����--%>
		</div>
	
	</body>
</html>