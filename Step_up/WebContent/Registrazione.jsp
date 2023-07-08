<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<title>Registrazione</title>
<link rel="stylesheet" type="text/css" href="style/Responsive.css">
<link rel="stylesheet" type="text/css" href="style/Form.css">
<%@include file="Header.jsp"%>
</head>
<body>
	<div id="page">

		<form action="CreateAccount" name="account" method="POST">
			<h2>Creazione account</h2>
			<p>Unisciti a noi!<br> Compila i seguenti campi del form per creare
				il tuo account su Step Up.</p>
			<div id=tebleRow>
				<label for="email">Email: </label> <input type="email" name="email"
					placeholder="E-mail" maxlength="50">
			</div>
			<div id="tableRow">
				<label for="username">Username: </label> <input type="text"
					name="username" placeholder="Username" maxlength="50">
			</div>
			<div id="tableRow">
				<label for="password">Password: </label> <input type="password"
					name="password" placeholder="Password">
			</div>
			<button id="pulsante" type="submit" name="Iscriviti">Iscriviti</button>

		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>