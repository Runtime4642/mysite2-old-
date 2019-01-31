package com.douzone.mvc.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authuser");
		if(authUser ==null)
		{
			WebUtils.forward(request, response, request.getContextPath());
			return;
		}
		long userNo = authUser.getNo();
			String bNo = request.getParameter("bNo");
			String contents = request.getParameter("content");
			String title = request.getParameter("title");
			BoardVo vo = new BoardVo();
			vo.setContents(contents);
			vo.setNo((long)Integer.parseInt(bNo));
			vo.setTitle(title);			
			new BoardDao().replyWrite(vo, userNo);
			WebUtils.redirect(request, response, request.getContextPath() + "/board");
		
	}

}
