<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h1 align="center">URL Shortener</h1>
	<hr/><hr/>
	<h3 align="center" style="color: red"><c:out value="${message}"></c:out></h3>
	<h3> <a href="/login">Login</a> </h3>
	<h3> <a href="/register">Register</a> </h3>
</body>
</html>