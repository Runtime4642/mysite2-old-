package com.douzone.mvc.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
			String no = request.getParameter("no");
			String boardNo =request.getParameter("boardNo");
			System.out.println("delete no:"+no+", boardNo:"+boardNo);
			new BoardDao().commentDelete(no);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&c=true&no="+boardNo);
		
	}

}
