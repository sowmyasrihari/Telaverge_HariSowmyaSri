<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>
<link  rel="stylesheet" href="https://cdn.datatables.net/2.0.2/css/dataTables.dataTables.css">
<meta charset="ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<h1 align="center">URL Shortener</h1>
	<p align="right"><a href="/">Logout</a></p>
	<hr/><hr/>
	
	<h2>Welcome, ${name}</h2>
	
	
	<h3 align="center">Shorten URL</h3>
	<h4 align="center" style="color: blue;">${msg}</h4>
	<form action="/shortenurl" method="post">
		<table align="center">
			<tr>
				<td>Enter the URL: </td>
				<td> <input type="text" required="required" name="url"> </td>
			</tr>
			<tr> <td colspan="2" align="center"> <input type="submit" value="Shorten"> </td> </tr>
		</table>
	</form>
	<br/><br/><br/>
	<h3 align="center">Your List</h3>
	<table id = "datatable">
	<thead>
	<tr>
		<th>Original URL</th>
		<th>Short URL</th>
		<th>Number of Clicks</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${urllist}" var="u">
			<tr>
				<td> ${u.url}</td>
				<td> <a target="_blank" href="/url/${u.shorturl}">url/${u.shorturl}</a></td>
				<td>${u.accessedCount}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>

	<script type="text/javascript">
	 new DataTable("#datatable");
	</script>
</body>
</html>