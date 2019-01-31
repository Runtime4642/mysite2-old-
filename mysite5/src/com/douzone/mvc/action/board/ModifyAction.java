package com.douzone.mvc.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.UserVo;


public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		String contents = request.getParameter("content");
		String title = request.getParameter("title");
		String no = request.getParameter("no");
		new BoardDao().modify(no, title, contents);
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
