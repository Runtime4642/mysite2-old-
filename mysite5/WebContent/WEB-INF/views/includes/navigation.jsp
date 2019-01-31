<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<c:choose>
					<c:when test='${param.menu == "main" }'>
						<li class="selected"><a href="<c:url value='/' />">정영석</a></li>
						<li><a href="<c:url value='/' />gb">방명록</a></li>
						<li><a href="<c:url value='/' />board">게시판</a></li>
					</c:when>
					<c:when test='${param.menu == "guestbook" }'>
						<li><a href="<c:url value='/' />">정영석</a></li>
						<li class="selected"><a href="<c:url value='/' />gb">방명록</a></li>
						<li><a href="<c:url value='/' />board">게시판</a></li>
					</c:when>
					<c:when test='${param.menu == "board" }'>
						<li><a href="<c:url value='/' />">정영석</a></li>
						<li><a href="<c:url value='/' />gb">방명록</a></li>
						<li class="selected"><a href="<c:url value='/' />board">게시판</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value='/' />">정영석</a></li>
						<li><a href="<c:url value='/' />gb">방명록</a></li>
						<li><a href="<c:url value='/' />board">게시판</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>