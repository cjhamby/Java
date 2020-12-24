<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chris Store</title>
</head>
<body>
Success!
<br />
<% 
	if(session.getAttribute("loginSuccess") != null) {
		out.println("welcome " + session.getAttribute("uname"));
	}
%>
<br />
<a href="index.jsp">return to home page</a>
</body>
</html>