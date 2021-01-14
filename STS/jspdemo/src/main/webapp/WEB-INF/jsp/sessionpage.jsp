<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>session and response tests</h2>
<h3>add a cookie to the session</h3>

<form method="POST" action="/addcookie">
cookie value<input type="text" name="value" />
<input type="submit" value="go"/>
</form>

<h3>Cookie List</h3>
<c:forEach items="<%= request.getCookies() %>" var="cookie">
<c:out value="${cookie}"></c:out></c:forEach>
</body>
</html>