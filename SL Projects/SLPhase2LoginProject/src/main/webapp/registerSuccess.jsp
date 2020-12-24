<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
<h1>New Account Created for
<%
	if(session.getAttribute("regname") != null) {
		out.println( " " + session.getAttribute("regname"));
	}
%>
!</h1>
<a href="index.html">return to home page</a>
</body>
</html>