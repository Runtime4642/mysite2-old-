package com.douzone.mvc.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;


public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/reply.jsp");
		
	}

}
