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
<h2>Product Log</h2>
Enter a product ID to retrieve more details about it <br/>

<form method="GET" action="DetailsServlet">
ID:<input type="text" name="pid" />
<input type="submit" value="Submit"/>
</form>

<%
	if(session.getAttribute("details") != null){
		out.println(session.getAttribute("details"));
	}
%>

</body>
</html>
