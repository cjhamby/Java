<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Page</title>
</head>
<body>
<form action="/users" method="GET">
Search by user Id: <input type="text" name="uid"/>
<input type="submit" value="Search" />
</form>
<a href="/users/all">show all users</a><br/>
<a href="/users/add">populate database with sample users</a>
</body>
</html>