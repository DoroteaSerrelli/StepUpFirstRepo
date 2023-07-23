<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<title>Info Registrazione</title>
</head>
<body>
<%@include file = "Header.jsp" %>
<div id = "page">
	<h2>Informazioni generali per la registrazione</h2>

	<p class = "information">Per poterti registrare al nostro sito web in modo da accedere alla wishlist, acquistare prodotti ed accedere ad altri
	numerosi vantaggi, è necessario autenticarsi al seguente link <a href = "<%= request.getContextPath()%>/Registrazione.jsp">Registrazione</a>.
	Inserisci l'username, ossia una sequenza di al massimo 25 caratteri non numerici, ed una password che ha almeno 5 caratteri ed al massimo 20 caratteri
	, di cui almeno una lettera ed un numero.</p>
	<br>
	<br>
</div>	

<%@ include file="Footer.jsp" %>
</body>
</html>