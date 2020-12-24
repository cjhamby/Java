<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chris Store</title>
</head>
<body>
<a href="index.jsp">return home</a> <br/>
<h1>Product Details</h1>

<h3>
<% 
	if(session.getAttribute("productName") != null) {
		out.println(session.getAttribute("productName"));
	}
%>
</h3>
<%
	if(session.getAttribute("productCost") != null) {
		out.println("Cost: $" + session.getAttribute("productCost"));
	}
%>
<br/>
<%
	if(session.getAttribute("productID") != null) {
		out.println("ID: " + session.getAttribute("productID"));
	}
%>

</body>
</html>