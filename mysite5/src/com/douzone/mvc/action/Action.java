package com.douzone.mvc.action;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
public interface Action {
	public void execute(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException,IOException;
}
