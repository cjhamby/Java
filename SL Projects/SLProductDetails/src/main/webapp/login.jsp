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
<h1>Log in to your Chris Store Account</h1>
<form method="POST" action="LoginServlet">
Email <input type="text" name="email" /><br/>
Password <input type="text" name="pass" /><br/>
<input type="submit" value="Go" />
</form>

<% 
	if(session.getAttribute("loginSuccess") != null) {
		if( !(boolean)session.getAttribute("loginSuccess") ) {
			out.println("Invalid Credentials, try again");
		}
	}
%>
</body>
</html>