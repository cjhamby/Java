package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.DBHelper;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass  = request.getParameter("password");
		
		if( DBHelper.addAccount(uname, pass) ) {
			request.getSession().setAttribute("registerSuccess", true);
			request.getSession().setAttribute("regname", uname);
			response.sendRedirect("registerSuccess.jsp");
		} else {
			request.getSession().setAttribute("registerSuccess", false);
			response.sendRedirect("register.jsp");
		}
	}
}
