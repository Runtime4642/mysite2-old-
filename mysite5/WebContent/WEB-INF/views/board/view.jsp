<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%pageContext.setAttribute("newline", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function showList(){
	target = document.getElementById("default");
	if( target.style.display =="none")
    target.style.display = "block";
	else
		target.style.display = "none";
}

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="3">글보기</th>
					</tr>
					<c:if test="${not empty boardVo.fileName}">
					<tr>
						<td class="label"></td>
						<td id="attach-label"><a id='attach' onclick="showList()" >첨부파일</a><br>
						<ul id="default" style="display:none;">
							<li id="attach-down"><a href="<c:url value='/uploads/' />${boardVo.fileName}" download>파일다운로드</a></li>
							
						</ul>
						</td>
						
					</tr>
					</c:if>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(boardVo.contents, newline, "<br>") }
							</div>
						</td>
					</tr>
				
				</table>
				<div class="bottom">
					<a href="<c:url value='/' />board">글목록</a>
				<c:choose>
					<c:when test="${not empty authuser}">
					<a href="<c:url value='/' />board?a=replyForm&no=${boardVo.no}">답글달기</a>
					</c:when>
				</c:choose>	
				<c:if test="${userNo == authuser.no}">
					<a href="<c:url value='/' />board?a=modifyForm&no=${boardVo.no}">글수정</a>
					</c:if>	
				</div>
				
				
				<!-- 댓글 보기창 -->
				
					<table class="tbl-ex2">
					<tr>
						<th colspan="3">댓글</th>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">		
					<tr>
						<td class="label" id="comment-Writer">${vo.userVo.name}</td>
						<td id="comment-content">${vo.contents}</td>
						<c:choose>
							<c:when test="${authuser.no==vo.userVo.no}">
						<td id="comment-delete"><a href="<c:url value='/' />board?a=commentdelete&no=${vo.no}&boardNo=${boardVo.no }" class="del"></a></td>
							</c:when>
							<c:otherwise>
						<td id="comment-delete"> </td>
							</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</table>
				
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:set var="loop" value="true"/>
						<c:forEach  begin="${bStart}" end="${bEnd}" step="1" var="i" >
						<c:if test="${loop}">
						<c:if test="${bStart != 1}">
							<li id="pre"><a href="<c:url value='/' />board?a=view&id=${i-4}&no=${boardVo.no }">◀ </a></li>
						</c:if>
							<li id="${i}" class=""><a href="<c:url value='/' />board?a=view&id=${i}&no=${boardVo.no }">${i} </a></li>
						<c:if test="${i==5 && bEnd-bStart >=5}">
							<li id="next"><a href="<c:url value='/' />board?a=view&id=${i+1}&no=${boardVo.no }">▶</a></li>
							<c:set var="loop" value="false"/>	
						</c:if>
						</c:if>
						</c:forEach>
					</ul>
				</div>	
				<!-- pager 추가 -->
				
				
				<br/>
					<script>
				document.getElementById('${id}').className="selected"
				</script>
				
				<br/>
				
				<!--  댓글 입력창 -->
				<c:if test="${not empty authuser}">
				<form action="<c:url value='/' />board" method="post">
				<input type="hidden" name="a" value="comment">
				<input type="hidden" name="no" value="${boardVo.no}">
				<div class="comment">
				<input type="textbox" id="comment-text" name="text" value="">
				<input type="submit" value="댓글입력" id="comment-input">
				</div>
				</form>
				</c:if>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="board" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>