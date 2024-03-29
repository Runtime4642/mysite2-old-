package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.MainActionFactory;

//s@WebServlet("")
public class MainServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {

		String configPath = getServletConfig().getInitParameter("config");
		System.out.println("init() called"+configPath);
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		AbstractActionFactory af = new MainActionFactory();
		Action action = af.getAction(actionName);
		action.execute(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
