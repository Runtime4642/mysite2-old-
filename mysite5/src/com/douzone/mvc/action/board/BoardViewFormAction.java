package com.douzone.mvc.action.board;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;

public class BoardViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String boardNo = request.getParameter("no");
		String userNo = request.getParameter("userNo");
		System.out.println(userNo);
		String boolcomment = request.getParameter("c");
		System.out.println(id);
		if(id==null)
			id="1";
		
		int bCnt= new BoardDao().CommentCount(Integer.parseInt(boardNo));
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
	
		BoardVo vo = new BoardDao().view(boardNo);
		request.setAttribute("userNo", userNo);
		request.setAttribute("boardVo",vo);
		request.setAttribute("bEnd", bEnd);
		request.setAttribute("bStart",bStart);
		request.setAttribute("id", id);
		
		
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		int count =0;
		if(cookies !=null && cookies.length >0)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals(boardNo))
				{
					count= Integer.parseInt(c.getValue());
				}
			}
		}
		if(count==0 && boolcomment==null) //처음 들어왔고 댓글적는게 아닐경우
		{
				new BoardDao().hit(boardNo);
		}
		// 쿠키 쓰기
		count++;
		Cookie cookie = new Cookie(boardNo,""+ count);
		cookie.setMaxAge(24*60*60);
		cookie.setPath(request.getContextPath()+"/board");
		response.addCookie(cookie);
		
		List <CommentVo> list = new BoardDao().getCommentList(Integer.parseInt(id), Integer.parseInt(boardNo));
		request.setAttribute("list",list);
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
		
		
	
		
	}

}
