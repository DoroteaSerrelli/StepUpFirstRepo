<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<title>Privacy</title>
</head>
<body>
<%@include file = "Header.jsp" %>
<div id = "page">
<h2>Informativa sulla privacy</h2>
		<p class="information">I dati forniti in fase di Registrazione al
			Sito o in fase di Semplice Acquisto dei nostri prodotti,
			semplicemente identificativi come ad esempio: nome, cognome,
			indirizzo, email e numero telefonico. Questi sono i dati che ci hai
			fornito. I dati legati alle tue preferenze ed interessi, in
			particolare: l’uso dei servizi, gli acquisti eseguiti, i negozi
			presso cui hai acquistato, le funzionalità utilizzate, i tempi di
			connessione, i dati di traffico, i dati di navigazione su siti e
			profili social delle aziende del gruppo Step Up o
			di partner commerciali o di terzi soggetti, l’indirizzo IP,
			l’identificativo del device, i device e le connettività utilizzate.
			Questi dati possono essere raccolti anche per mezzo di cookies e
			metadati.</p>

	</div>	

<%@ include file="Footer.jsp" %>
</body>
</html>