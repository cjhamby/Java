<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Notes on Spring (Web) MVC Controllers</h1>
The first thing to know is that you can't use jsp file names in hyperlinks: &emsp;
href="file.jsp" <i>will not work</i>
<h3>Accessing JSP pages with MVC Controllers</h3>
NotesController is responsible for directing requests for "/mynotes" to "notes.jsp"<br/>
Check out the file and you'll see some Spring Boot annotations:
<ul>
<li>@Controller designates the class as a Spring Boot MVC Controller</li>
<li>@RequestMapping designates the requests that the controller serves</li>
<li>@GetMapping designates the thing that happens when the controller serves a HTTP "GET" request</li>
</ul>
<h3>Routing JSP pages with a Configuration File</h3>
Creating view controllers is easy to do with a configuration class<br/>
<code>SampleConfig.java</code> serves the following request:&emsp;<a href="/config">Go</a>
</body>
</html>