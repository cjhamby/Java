<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback portal</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Feedback Portal</h1>
I don't actually know what a portal is but it sounds good at least<br/>
<a href="/swagger-ui.html">documentation</a>
<p>
<h3>Leave some feedback</h3>
<div class="newFeedback">
<form:form modelAttribute="feedback" action="/feedback" method="POST">
	<form:label path="message">Leave a comment</form:label><br/>
	<form:input path="message"/><br/><br/>
	<form:label path="numStars">how many stars would you give us?</form:label><br/>
	<form:input path="numStars"/><br/><br/>
	<input type="submit" value="Leave Feedback"/>
</form:form>
</div>
<h3>What People Are Saying</h3>
<c:forEach items = "${feedbacks}" var="feedback">
	<div class="feedbackBox">
	${feedback.message}<br/>
	${feedback.numStars} Stars!
	</div>
</c:forEach>
</body>
</html>