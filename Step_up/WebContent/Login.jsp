<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<title>Login</title>
<%@include file = "Header.jsp" %>
<link type = "text/css" rel= "stylesheet" href = "style/Responsive.css">
<link type = "text/css" rel= "stylesheet" href = "style/Form.css">
</head>

<body>
<div id = "page">

	<%
	List<String> errors = (List<String>) request.getAttribute("errors");
	if (errors != null) {
		for (String error : errors) {
	%>
	<%=error%>
	<br>
	<%
		}
	}
	%>
	
	<form name="login" method="post" action="LoginCheck">
		<h2>Login</h2>
		<p>Accedi a tutti i tuoi acquisti e servizi presso Step up</p>
		<fieldset>
		<div id = "tableRow">
			<label for="username">Username</label>
			<input type="text" placeholder="Inserisci username" name="username">
		</div>
			<br>
		<div id = "tableRow">
			<label for="password">Password</label>
			<input type="password" placeholder="Inserisci password" name="password">
		</div>
			<br>
			<button id = "pulsante" type="submit">Accedi</button><br>
			<button id = "pulsante" type="reset">Reset</button>

		</fieldset>
	</form>
	<p>Non hai un account?</p>
	<a href = "Registrazione.jsp"><button type = "button" id = "pulsante" name = "Registrazione">Registrati</button></a>
	</div>
<%@ include file="Footer.jsp" %>
</body>
</html>