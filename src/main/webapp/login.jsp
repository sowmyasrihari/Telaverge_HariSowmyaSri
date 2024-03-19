<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1 align="center">URL Shortener</h1>
	<hr/><hr/>
	
	<h3 align="center">Login</h3>
	<h4 align="center" style="color: green;"> <c:out value="${message}"></c:out> </h4>
	<form action="/authenticate" method="post">
		<table align = "center" border="3">
			<tr>
				<td>Enter Email: </td>
				<td> <input type="email" name="username" required="required"></td>
			</tr>
			<tr>
				<td>Enter Password: </td>
				<td> <input type="password" name="password" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <input type="submit" value="Login"></td>
			</tr>
		</table>	
	</form>
	<h3> <a href="/register">Register</a> </h3>
</body>
</html>