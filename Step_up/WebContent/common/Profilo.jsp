<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornamento profilo</title>
<script src = "../scripts/Validate.js"></script>
<%@include file = "../Header.jsp" %>
</head>
<body>

<form name = "datiPersonali" action = "UpdateProfile" enctype = "application/x-www-form-urlencoded" method = "POST">
		<fieldset>
		<legend>Informazioni personali</legend>
			<label for="Nome">Nome</label>
			<input type = "text" name = "nome" value = "" placeholder = "Nome">
			<br>
			<label for="Cognome">Cognome</label>
			<input type="text" name = "cognome" value = "" placeholder = "Cognome">
			<br>
			<label for="telefono">Numero di telefono</label>
			<input type="tel" name = "telefono" value = "" placeholder = "Telefono Cellulare">
			<br>
			<label for = "sesso">Sesso</label><br>
			<input type = "radio" name = "sesso" value = "Uomo">Uomo
			<br>
			<input type = "radio" name = "sesso" value = "Donna">Donna
			<br>
			<input type = "text" name = "Indirizzo1">
		</fieldset>
		<input type = "submit" value = "Aggiorna il profilo">
		</form>
		

		
		<%@ include file="../Footer.jsp" %>
</body>
</html>