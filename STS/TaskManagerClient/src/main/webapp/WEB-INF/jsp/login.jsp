<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
</head>
<body>
<h2>Login</h2>
<form action="/login" method="POST">
Name <input type="text" name="name"/><br/>
Pass <input type="text" name="pass"/><br/>
<input type="submit" value="Go"/>
</form>
</body>
</html>