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

<h1>Add a Product</h1>
Enter the product details below.
<form method="POST" action="AddProductServlet">
Name: <input type="text" name="pname" /> <br/>
ID#: <input type="text" name="pid" /> <br/>
Cost: <input type="text" name="pcost" /> <br/>
<input type="submit" value="Add Product" /> <br>

<%
Boolean added = (Boolean)session.getAttribute("addSuccess");
	if(added != null){
		if( added ) {
			out.print("successfully added product: ");
			out.println((String)session.getAttribute("lastAddedProduct"));
		} else {
			out.println("could not add product");
		}
	}
%>
</form>
</body>
</html>