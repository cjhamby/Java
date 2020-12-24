package com.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.App;
import com.dao.Product;


public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	/**
	 * Looks for the product requested by an id value
	 * sets session attributes to the product's details
	 * redirects to the appropriate page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		try {
			int pid = Integer.parseInt(request.getParameter("pid"));
			Product product = App.getProduct(pid);
			if(product == null) {
				session.setAttribute("details", "product not found");
				session.setAttribute("productName", null);
				session.setAttribute("productID", null);
				session.setAttribute("productCost", null);
				response.sendRedirect("search.jsp");
			} else {
				session.setAttribute("details", "product found");
				session.setAttribute("productName", product.getProductName());
				session.setAttribute("productID", product.getProductId());
				session.setAttribute("productCost", product.getProductCost());
				response.sendRedirect("detailpage.jsp");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("details servlet - doGet threw " + e);
		}
		
		
	}
}
