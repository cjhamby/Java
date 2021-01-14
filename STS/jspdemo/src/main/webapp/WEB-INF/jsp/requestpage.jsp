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
<h2>JSP Tests on request Object</h2>
Get Session<br/>
<%= request.getSession() %>
<br/>
<br/>
Content Type<br/>
<%= request.getContentType() %>
<br/>
<br/>
Header Names<br/>
<%= request.getHeaderNames() %>
<br/>
<br/>
JSTL with an embedded JSP implicit object: <i>headerValues</i>
<br/>
<c:forEach items="${ headerValues }" var="header">
	<c:out value="${ header }"></c:out><br/>
</c:forEach>
<br/>
<br/>
<br/>
JSTL with an embedded JSP expression: <i>parameter names</i>
<br/>
<c:forEach items="<%= request.getParameterNames().asIterator() %>" var="header">
	<c:out value="${ header }"></c:out><br/>
</c:forEach>
</body>
</html>