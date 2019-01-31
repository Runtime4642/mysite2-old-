package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;


public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 접근제어(보안)
		UserVo authUser = null;
		HttpSession session = request.getSession();
		
		if(session != null)
		{
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser ==null)
		{
			WebUtils.forward(request, response, request.getContextPath());
			return;
		}
		String newName = request.getParameter("name");
		String newPassword = request.getParameter("password");
		String newGender = request.getParameter("gender");
		UserVo vo = new UserVo();
		vo.setName(newName);
		vo.setGender(newGender);
		vo.setPassword(newPassword);
		boolean result = new UserDao().update(vo,authUser.getNo());
		authUser.setName(newName);
		request.setAttribute("result",result);
		WebUtils.forward(request, response, "WEB-INF/views/user/modifyform.jsp");
	}
	
	

}
