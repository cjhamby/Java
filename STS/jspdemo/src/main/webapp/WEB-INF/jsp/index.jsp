<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home page</title>
</head>
<body>
<h1>Welcome to JSP Demo</h1>
This is meant to provide a starting point for JSP + Spring Boot projects<br/>
You want to have a bit of background knowledge on each, though...
<p>
<a href="mynotes">Notes on MVC</a>
<h3>How to Use JSP</h3>
An embedded tomcat server is included in various Spring Boot Starters, such as the Spring Boot Web Starter.<br/>
This means that it includes everything needed to run the Tomcat server without any external installations.<br/>
This is nice, but the embedded server doesn't include JSP support.
<p>
To get JSP support requires the Jasper JSP engine.<br/>
This can be imported easily by adding the following in the pom:
<p>
<code>
&#60;dependency&#62;<br/>
&emsp;&#60;groupId&#62; org.apache.tomcat.embed &#60;/groupId&#62;<br/>
&emsp;&#60;artifactId&#62; tomcat-embed-jasper &#60;/artifactId&#62;<br/>
&#60;/dependency&#62;
</code>
<p>
JSP files should be placed in the following default location:&emsp;<i>src/main/webapp/WEB-INF/jsp/</i>

<h3>How to Configure</h3>
Changes made in: <i>src/main/resources/application.properties</i>
<p>
Essentials for JSP
<ul>
	<li>add: spring.mvc.view.prefix=/WEB-INF/jsp/</li>
	<li>add: spring.mvc.view.suffix=.jsp</li>
</ul>
<p>
Hibernate, MySQL
<ul>
	<li>Not necessary for JSP, but included for reference</li>
	<li>spring.jpa.hibernate.ddl-auto=update</li>
	<li>spring.datasource.url=jdbc:mysql://localhost:3306/mysql</li>
	<li>spring.datasource.username=root</li>
	<li>spring.datasource.password=pass</li>
</ul>
<p>
Optional
<ul>
<li>server.port=8181 (or whatever port you'd like)</li>
</ul>
</body>
</html>