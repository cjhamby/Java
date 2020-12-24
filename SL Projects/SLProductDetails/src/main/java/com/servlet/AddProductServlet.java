package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dal.App;
import com.dao.Product;
import com.utility.HibernateUtility;

/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			String productName = (String)request.getParameter("pname");
			int productID = Integer.parseInt((String)request.getParameter("pid"));
			double productCost = Double.parseDouble((String)request.getParameter("pcost"));
			
			Product product = new Product(productID, productName, productCost);
			App.addProduct(product);
			session.setAttribute("addSuccess", true);
			session.setAttribute("lastAddedProduct", productName);
			
		} catch (Exception e) {
			System.out.println(e);
			session.setAttribute("addSuccess", false);
			
		}
		response.sendRedirect("addproduct.jsp");
	}

}
