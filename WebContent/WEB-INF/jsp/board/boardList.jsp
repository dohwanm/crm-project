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
				//검색 후 검색 대상과 검색 단어 출력
				if("<c:out value='${data.keyword}'/>"!=""){
					$("#keyword").val("<c:out value='${data.keyword}'/>");
					$("#search").val("<c:out value='${data.search}'/>");
				}
				
				//한 페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지하기 위한 설정
				if("<c:out value='${data.pageSize}'/>"!=""){
					$("#pageSize").val("<c:out value='${data.pageSize}'/>");
				}
			
				
				//검색 대상이 변경시 처리
				$("#search").change(function(){
					if($("#search").val()=="all"){
						$("#keyword").val("전체 데이터 조회합니다");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}
				});
				
				//검색 버튼 클릭시
				$("#searchData").click(function(){			
					if($("#srarch").val()!="all"){
						if(!chkSubmit($("#keyword"),"검색어를")) return;
					}
					goPage(1);
				});
				
				//한 페이지에 보여줄 레코드 수 변경될 대마다 처리 
				$("#pageSize").change(function(){
					goPage(1);
				});
				
				//글쓰기 버튼 클릭 시 처리 이벤트
				$("#writeForm").click(function(){
					location.href="/board/writeForm.htk";
				});
				
				//제목 클릭시 상세 페이지 이동
				$(".goDetail").click(function(){
					var h_Num = $(this).parents("tr").attr("data-num");
					$("#h_Num").val(h_Num);
					//상세 페이지로 이동하기 위해 form 추가 (id : detailForm)
					$("#detailForm").attr({
						"method":"get",
						"action":"/board/boardDetail.htk"
					});
					$("#detailForm").submit();
				});
				
			});
			//정렬 버튼 클릭 시 처리 함수
			function setOrder(order_by){
				$("#order_by").val(order_by);
				if($("#order_sc").val()=="DESC"){
					$("#order_sc").val("ASC");
				}else{
					$("#order_sc").val("DESC");
				}
				
				goPage(1);
			}
			
			//검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 처리
			
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
			<div id="boardTit"><h3>글목록</h3></div>
			<form name="detailForm" id="detailForm">
				<input type="hidden" name="h_Num" id="h_Num">
				<input type="hidden" name="page"  value="${data.page}">
				<input type="hidden" name="pageSize" value="${data.pageSize}">
			</form>
			
			<%-- 검색 기능 시작 --%>
			
			<div id="boardSearch">
				<form id="f_search" name="f_search">
					<input type="hidden" name="page" id="page" value="${data.page}">
					<input type="hidden" id="order_by" name="order_by" value="${data.order_by}">
					<input type="hidden" id="order_sc" name="order_sc" value="${data.order_sc}">
					
						<table summary="검색">
							<colgroup>
								<col width="70%"></col>
								<col width="30%"></col>
							</colgroup>
							<tr>
								<td id="btd1">
									<label>검색 조건</label>
									<select id="search" name="search">
										<option value="all">전체</option>
										<option value="h_Title">제목</option>
										<option value="h_Content">내용</option>
										<option value="h_Name">작성자</option>
									</select>
									<input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요">
									<input type="button" value="검색" id="searchData">
								</td>
								<td id="btd2">한 페이지에
									<select id="pageSize" name="pageSize">
										<option value="1">10줄</option>
										<option value="2">20줄</option>
										<option value="3">30줄</option>
										<option value="40">40줄</option>
										<option value="50">50줄</option>
										<option value="60">60줄</option>
										<option value="70">70줄</option>
									</select>
								</td>
							</tr>
						</table>
				</form>
			</div>
			<%--검색 기능 종료 --%>
			<%-- 리스트 시작 --%>
			<div id="boardList">
			
				<table summary="게시판 리스트">
				
					<colgroup>
					
						<col width="10%"/>
						<col width="62%"/>
						<col width="15%"/>
						<col width="13%"/>
					</colgroup>
					
					<thead>
					
					<tr>
						<th>
							<a href="javascript:setOrder('h_Num');">글번호
								<c:choose>
									<c:when test="${data.order_by=='h_Num'and data.order_sc=='ASC'}">▲</c:when>
									<c:when test="${data.order_by=='h_Num'and data.order_sc=='DESC'}">▼</c:when>
									<c:otherwise>▲</c:otherwise>
								</c:choose>
							</a>
						</th>
						<th>글제목</th>
						<th>
							<a href="javascript:setOrder('h_Insertdate');">작성일
								<c:choose>
									<c:when test="${data.order_by=='h_Insertdate'and data.order_sc=='ASC'}">▲</c:when>
									<c:when test="${data.order_by=='h_Insertdate'and data.order_sc=='DESC'}">▼</c:when>
									<c:otherwise>▲</c:otherwise>
								</c:choose>
							</a>
						</th>
						
						<th class="borcle">작성자</th>
					</tr>
					
					</thead>
					<tbody>
					<!-- 데이터 출력 -->
					
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
								<td colspan="4" align="center">등록된 게시물이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					</tbody>
				</table>
			</div>
			<%-- 리스트 종료 --%>
			
			<%-- 글쓰기 버튼 출력 시작--%>
			<div id="boardBut">
				<input type="button" value="글쓰기" id="writeForm">
			</div>
			<%-- 글쓰기 버튼 출력 종료 --%>
			
			
			
			<%-- 페이지 네비게이션 시작--%>
			
			<div id="boardPage">
				<tag:paging page ="${param.page}" total="${total}" list_size="${data.pageSize}" />
			</div>
			
			<%-- 페이지 네비게이션 종료--%>
		</div>
	
	</body>
</html>