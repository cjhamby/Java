package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Account;
import com.server.DBHelper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass  = request.getParameter("password");
		
		Account account = DBHelper.getAccount(uname);
		if(account == null) {
			response.sendRedirect("login.jsp");
		}
		if(account.getPass().equals(pass)){
			request.getSession().setAttribute("username", uname);
			request.getSession().setAttribute("loginSuccess", true);
			response.sendRedirect("welcome.jsp");
		} else {
			request.getSession().setAttribute("username", null);
			request.getSession().setAttribute("loginSuccess", false);
			response.sendRedirect("login.jsp");
		}
		
	}

}
