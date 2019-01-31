package com.douzone.mvc.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

public class CommentInputAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authuser");
		if(authUser ==null)
		{
			WebUtils.forward(request, response, request.getContextPath());
			return;
		}
		String contents = request.getParameter("text");
		String boardNo = request.getParameter("no");
		
		CommentVo vo = new CommentVo();
		vo.setContents(contents);
		
		new BoardDao().commentWrite(vo, authUser.getNo(), (long)Integer.parseInt(boardNo));
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&c=true&no="+boardNo);
		
		
	}

}
