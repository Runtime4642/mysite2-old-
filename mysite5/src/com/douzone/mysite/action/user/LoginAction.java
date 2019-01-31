package com.douzone.mysite.action.user;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.mysql.jdbc.Util;
import javax.servlet.ServletException;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		UserVo authuser =new UserDao().get(email,password);
		
		// 인증실패
		if(authuser==null) {
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		// 인증성공 -> 인증처리
		HttpSession session= request.getSession(true);
		session.setAttribute("authuser", authuser);
		
		// main redirect 
		WebUtils.redirect(request, response, request.getContextPath());
		
	}

}
