package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pass  = (String)request.getParameter("pass");
		String email = (String)request.getParameter("email");
		
		if(pass.equals("totallyrealpassword") && email.equals("account@totallyrealemail.com")) {
			request.getSession().setAttribute("loginSuccess", true);
			request.getSession().setAttribute("uname", "totally real person");
			response.sendRedirect("success.jsp");
		} else  {
			request.getSession().setAttribute("loginSuccess", false);
			response.sendRedirect("login.jsp");
		}
		
		
	}

}
