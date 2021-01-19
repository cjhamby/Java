<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>WOO</h1>
You made it to user home: ${ user.userName }<br/>
<a href="/addTask">Add a new task</a>
<h3>tasks</h3>
<c:forEach items = "${user.userTasks}" var="task">
	${task.taskName }	
</c:forEach>
</body>
</html>