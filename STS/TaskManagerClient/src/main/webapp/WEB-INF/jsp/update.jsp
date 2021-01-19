<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
</head>
<body>
<h1>update a task</h1>
<form:form modelAttribute="task" action="/modify" methodParam="post" >
	<form:hidden path="taskId" value="${task.taskId }"/>
	Task Name 	<form:input path="taskName" type="text" /><br/>
	Task Desc 	<form:input path="taskDesc" type="text" /><br/>
	Start Date 	<form:input type="date" path="startDate"/><br/>
	End Date 	<form:input type="date" path="endDate"/><br/>
	Email 		<form:input type="text" path="email"/><br/>
	Priority	
	<select name="priority">
		<option value="low">Low</option>
		<option value="med">Medium</option>
		<option value="high">High</option>
	</select><br/><br/>
	<input type="submit" value="Add Task"/>
</form:form>
</body>
</html>