package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				HttpSession session = request.getSession();
				UserVo authUser = (UserVo)session.getAttribute("authuser");
				if(session != null && session.getAttribute("authuser") != null)
				{
				// 로그아웃 처리
				session.removeAttribute("authuser"); //--> 세션 삭제
				session.invalidate(); // --> 세션아이디를 다시 만드는 매소드

				}

				WebUtils.redirect(request, response, request.getContextPath());
				
	}

}
