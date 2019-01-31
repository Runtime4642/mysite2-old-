<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="<c:url value='/' />board" method="post">
				<input type="hidden" name="a" value="search">
				<select name="search-value">
			<option value="writerSearch">글쓴이</option>  <!-- value시 1991년이 아니라 value의 값이 넘어감  -->
			<option value="titleSearch">제목</option>
							</select>
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>첨부파일</th>
						<th>&nbsp;</th>
					</tr>
						<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list}" var="vo" varStatus="status">			
					<tr>
						<td>[ ${count-status.index } ] </td>
						<c:choose>
						<c:when test="${vo.depth > 0}">
						<td style="padding-left:${30*vo.depth }px"><img src="<c:url value='/' />assets/images/reply.png"/><a href="<c:url value='/' />board?a=view&no=${vo.no}&userNo=${vo.userVo.no}">${vo.title}</a></td>
						</c:when>
						<c:otherwise>
						<td style="padding-left:${30*vo.depth }px"><a href="<c:url value='/' />board?a=view&no=${vo.no}&userNo=${vo.userVo.no}">${vo.title}</a></td>
						</c:otherwise>
						</c:choose>
						<td>${vo.userVo.name}</td>
						<td>${vo.hit}</td>
						<td>${vo.writeDate}</td>
						<c:choose>
							<c:when test="${not empty vo.fileName}">
						<td><a href="<c:url value='/uploads/' />${vo.fileName}" download class="file"></a></td>
							</c:when>
							<c:otherwise>
								<td>&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${authuser.no==vo.userVo.no &&not empty vo.fileName}">
						<td><a href="<c:url value='/' />board?a=delete&no=${vo.no}&delFile=/${vo.fileName}" class="del"></a>
						</td>
							</c:when>
							<c:when test="${authuser.no==vo.userVo.no && empty vo.fileName}">
							<td><a href="<c:url value='/' />board?a=delete&no=${vo.no}" class="del"></a>
							</td>
							</c:when>
							<c:otherwise>
								<td>&nbsp;</td>
							</c:otherwise>
						</c:choose>
						
					</tr>
					</c:forEach>
				</table>
				
				
				
				<c:choose>
				<c:when test="${search==false}">
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:set var="loop" value="true"/>
						<c:forEach  begin="${bStart}" end="${bEnd}" step="1" var="i" >
						<c:if test="${loop}">
						<c:if test="${bStart != 1}">
							<li id="pre"><a href="<c:url value='/' />board?a=select&id=${i-4}">◀ </a></li>
						</c:if>
							<li id="${i}" class=""><a href="<c:url value='/' />board?a=select&id=${i}">${i} </a></li>
						<c:if test="${i==5 && bEnd-bStart >=5}">
							<li id="next"><a href="<c:url value='/' />board?a=select&id=${i+1}">▶</a></li>
							<c:set var="loop" value="false"/>	
						</c:if>
						</c:if>
						</c:forEach>
					</ul>
				</div>	
				<!-- pager 추가 -->
				</c:when>
				<c:otherwise>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:set var="loop" value="true"/>
						<c:forEach  begin="${bStart}" end="${bEnd}" step="1" var="i" >
						<c:if test="${loop}">
						<c:if test="${bStart != 1}">
							<li id="pre"><a href="<c:url value='/' />board?a=search&id=${i-4}&search-value=${searchValue}&kwd=${text}">◀ </a></li>
						</c:if>
							<li id="${i}" class=""><a href="<c:url value='/' />board?a=search&id=${i}&search-value=${searchValue}&kwd=${text}">${i} </a></li>
						<c:if test="${i==5 && bEnd-bStart >=5}">
							<li id="next"><a href="<c:url value='/' />board?a=search&id=${i+1}&search-value=${searchValue}&kwd=${text}">▶</a></li>
							<c:set var="loop" value="false"/>	
						</c:if>
						</c:if>
						</c:forEach>
					</ul>
				</div>	
				<!-- pager 추가 -->
				
				
				</c:otherwise>
				
				</c:choose>	
				<br/>
					<script>
				document.getElementById('${id}').className="selected"
				</script>
				
			
							
				
				
				<c:choose>
					<c:when test="${not empty authuser}">
				<div class="bottom">
					<a href="<c:url value='/' />board?a=writeForm" id="new-book">글쓰기</a>
				</div>		
					</c:when>
					<c:otherwise>
						<h2>글쓰기는 로그인이 필요합니다</h2>
					</c:otherwise>
				</c:choose>		
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
		</div>
</body>
</html>