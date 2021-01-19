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
Welcome to Task Manager, ${ user.userName }<br/><br/>
<a href="/addTask">Add a new task</a><br/>
<a href="/addTestTask">Add a sample task for debugging</a><br/>
<a href="/logout">Log Out</a>
<h2>Current Tasks</h2>
<c:forEach items = "${user.userTasks}" var="task">

	<h3>${task.taskId}: ${ task.taskName }</h3>
	Description: ${ task.taskDesc }<br/>
	Contact Email: ${ task.email }<br/>
	Start Date: ${ task.startDate }<br/>
	End Date: ${ task.endDate }<br/>
	Priority: ${ task.priority }<br/>

	<form:form modelAttribute="task" action="/modify" method="get">
		<form:hidden path="taskId" value="${task.taskId }" />
		<form:hidden path="taskName" value="${task.taskName }" />
		<form:hidden path="taskDesc" value="${task.taskDesc }" />
		<form:hidden path="email" value="${task.email }" />		
		<form:hidden path="startDate" value="${task.startDate }" />
		<form:hidden path="endDate" value="${task.endDate }" />
		<form:hidden path="priority" value="${task.priority }"/>
		<input type="submit" value="Edit Task"/>
	</form:form>

	<form:form modelAttribute="task" action="/remove" method="post">
		<form:hidden path="taskId" value="${task.taskId }" />
		<form:hidden path="taskName" value="${task.taskName }" />
		<form:hidden path="taskDesc" value="${task.taskDesc }" />
		<form:hidden path="email" value="${task.email }" />		
		<form:hidden path="startDate" value="${task.startDate }" />
		<form:hidden path="endDate" value="${task.endDate }" />
		<form:hidden path="priority" value="${task.priority }"/>
		<input type="submit" value="Remove Task"/>
	</form:form>
	
</c:forEach>
</body>
</html>