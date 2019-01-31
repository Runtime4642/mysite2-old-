package com.douzone.mvc.action.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;

public class BoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String delFile = request.getParameter("delFile");
		String uploads=request.getRealPath("uploads");
		if(delFile!=null) {
			
		System.out.println("삭제함:"+delFile);
		File f = new File(uploads+delFile);
		
		f.delete();
		}
		else
			System.out.println("파일없음");
		
		new BoardDao().delete(no);
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
		
	}
	

}
