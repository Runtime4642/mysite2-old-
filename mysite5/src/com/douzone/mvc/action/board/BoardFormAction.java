package com.douzone.mvc.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class BoardFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		System.out.println(id);
		if(id==null)
			id="1";
		List<BoardVo> list= new BoardDao().getList(Integer.parseInt(id));
		int bCnt= new BoardDao().BoardCount();
		int bEnd=0;
		int bStart=0;
		if (bCnt % 10 ==0)
			bEnd  = bCnt/10 ;
		else
			bEnd = bCnt/10+1;
		if(Integer.parseInt(id)%6!=0)
		bStart = Integer.parseInt(id) - (Integer.parseInt(id) % 6)+1;
		else
			bStart = Integer.parseInt(id) - (Integer.parseInt(id) % 6);
		request.setAttribute("list", list);
		request.setAttribute("bEnd", bEnd);
		request.setAttribute("bStart",bStart);
		request.setAttribute("search", "false");
		request.setAttribute("id", id);
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
	}

}
