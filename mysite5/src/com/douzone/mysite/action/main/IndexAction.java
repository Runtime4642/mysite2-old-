package com.douzone.mysite.action.main;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*Cookkie Test*/
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		int count=0;
		if(cookies !=null && cookies.length >0)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("visitCount"))
				{
					count= Integer.parseInt(c.getValue());
				}
			}
		}
		
		// 쿠키 쓰기
		count++;
		Cookie cookie = new Cookie("visitCount",""+ count);
		cookie.setMaxAge(24*60*60);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		
		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");
		
		
	}

}
