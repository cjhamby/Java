<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
You have given us feedback as follows:<br/>
Message: ${ feedback.message }<br/>
Number of Stars: ${feedback.numStars }<br/>
<a href="/">Return to home</a>
</body>
</html>