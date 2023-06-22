<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiornamento profilo - Step Up</title>
<%@include file = "Header.jsp" %>
</head>
<body>

<form name = "dati personali" action = "UpdateDatipersonali" enctype = "application/x-www-form-urlencoded">
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
		</fieldset>
		<fieldset>
		<legend>Indirizzi di spedizione</legend>
			<%int i = 1; %>
			<label for = "Indirizzo<%= i%>">Indirizzo <%= i %></label><br>
			
			<input type = "text" name = "Address<%=i %>" value = "" placeholder = "Inserisci Via e numero civico dell'indirizzo<%= i %>">
			<br>
			<input type = "text" name = "Città<%=i %>" value = "" placeholder = "Inserisci città dell'indirizzo<%= i %>">
			<br>
			<input type = "text" name = "CAP<%=i %>" value = "" placeholder = "Inserisci CAP dell'indirizzo<%= i %>">
			<br>
			<input type = "text" name = "Provincia<%=i %>" value = "" placeholder = "Inserisci città di provincia dell'indirizzo<%= i %>">
			<button><img src = "../images/Addbutton.png" width = "20px" height = "20px"></button>
		<!-- Se si clicca questo pulsante devo generare un nuovo inserimento di indirizzo -->
		</fieldset>

		<input type = "submit" name = "UpdateProfile" value = "Aggiorna il profilo">
		</form>
		<%@ include file="Footer.jsp" %>
</body>
</html>