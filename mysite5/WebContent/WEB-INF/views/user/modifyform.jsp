<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value='/' />assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="<c:url value='/' />user">
					<input type="hidden" name="a" value="modify"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					

					<label class="block-label" for="email">이메일</label>
					<h3>${email}</h3>
					
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test="${gender eq 'female'}">
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
						  	</c:when>
						  	<c:otherwise>
						<label>여</label> <input type="radio" name="gender" value="female">
						<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
										
					<input type="submit" value="수정하기">
					<c:choose>
						<c:when test="${result}">
							<h2>수정성공</h2>
						</c:when>
						<c:when test="${empty result}">
							
						</c:when>
						<c:otherwise>
							<h2>수정실패</h2>
						</c:otherwise>	
					</c:choose>
				</form>
				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>