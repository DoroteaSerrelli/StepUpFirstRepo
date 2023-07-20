<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Login</title>
</head>
<body>

	<%@include file="Header.jsp"%>
	<div id="page">

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
			<h2 class = "form-header">Login</h2>
			<p class = "form-text">Accedi a tutti i tuoi acquisti e servizi presso Step up</p>

			<div class="form-tableRow">
				<label for="username">Username</label> <input type="text"
					placeholder="Inserisci username" name="username">
			</div>
			<br>
			<div class="form-tableRow">
				<label for="password">Password</label> <input type="password"
					placeholder="Inserisci password" name="password">
			</div>
			<br>
			<div class="form-tableRow">
				<button class="pulsante" type="submit">Accedi</button>
				<button class="pulsante" type="reset">Reset</button>
			</div>

		</form>
		<div class="register-container">
			<p>Non hai un account?</p>
			<a href="Registrazione.jsp">
			<button type="button" class="pulsante" name="Registrazione">Registrati</button></a>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>