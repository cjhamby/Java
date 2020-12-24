<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<h1>
<%
	out.println("Welcome to the site, " + session.getAttribute("username"));
%>
</h1>

<form method="get" action="LogoutServlet">
	<input type="submit" value="Logout">
</form>

</body>
</html>