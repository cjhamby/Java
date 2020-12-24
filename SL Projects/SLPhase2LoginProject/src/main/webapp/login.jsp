<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Log In to Your Account</h1>
<form action="LoginServlet" method="POST">
Username <input type="text" name="username"/> <br/>
Password <input type="text" name="password"/> <br/>
<input type="submit" value="Go" />
<% 
	if(session.getAttribute("loginSuccess") != null) {
		if(!(boolean)session.getAttribute("loginSuccess")) {
			out.println("Invalid Credentials, Try Again!");
		}
	}
%>
</form>
</body>
</html>