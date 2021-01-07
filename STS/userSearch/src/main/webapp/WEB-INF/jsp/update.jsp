<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
</head>
<body>
Updating user: ${user.name}
<br/>
<form action="${user.userID}" method="POST">
Enter new Username: <input type="text" name="newName"/>
<input type="submit" value="Search" />
</form>
</body>
</html>