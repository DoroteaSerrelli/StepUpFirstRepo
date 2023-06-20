<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Step Up Login</title>
<link type="text/css" rel="stylesheet" href="Header.css">
<link type="text/css" rel="stylesheet" href="Footer.css">
<link type="text/css" rel="stylesheet" href="Login.css">

</head>
<body>
	<h1>Step up</h1>
	<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>

	<form name="login" method="post" action="LoginCheck">
		<h2>Login</h2>
		<p>Accedi a tutti i tuoi acquisti e servizi presso Step up</p>
		<fieldset>
			<label for="username">Username</label>
			<input type="text" placeholder="Inserisci username" name="username">
			<br>
			<label for="password">Password</label>
			<input type="password" placeholder="Inserisci password" name="password">
			<br>
			<input type="submit" value="Login"/>
     		<input type="reset" value="Reset"/>
		</fieldset>
	</form>

</body>
</html>