<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<h1>Register a New Account</h1>
<form action="RegisterServlet" method="POST">
Username <input type="text" name="username"/> <br/>
Password <input type="text" name="password"/> <br/>
<input type="submit" value="Go" />
<%
	if(session.getAttribute("registerSuccess") != null) {
		if( !(boolean)session.getAttribute("registerSuccess") ) {
			out.println("unable to create user, try again");
		}
	}
%>
</form>
</body>
</html>