<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.*, model.ProductDTO, org.json.simple.JSONArray, org.json.simple.JSONObject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Risultati ricerca</title>
</head>
<body>
	<%@include file = "Header.jsp" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div id="page">
		<div id="results">
			<h1>Risultati della ricerca</h1>

			<%-- Ottieni i risultati della ricerca dalla richiesta --%>
			<c:set var="risultati" value="${requestScope.searchResults}" />

			<%-- Verifica se ci sono risultati --%>
			<c:if test="${not empty risultati}">
				<%-- Ciclo forEach per visualizzare i risultati --%>
				<c:forEach var="prodotto" items="${risultati}">
					<p>
					${prodotto.topImage}
					${prodotto.nomeProdotto}
					${prodotto.descrizioneBreve}
					${prodotto.brand}
					${prodotto.prezzo}</p>
				</c:forEach>
			</c:if>

			<%-- Messaggio di nessun risultato trovato --%>
			<c:if test="${empty risultati}">
				<p>Nessun risultato trovato.</p>
			</c:if>
		</div>
	</div>

	<%@include file = "Footer.jsp" %>
</body>
</html>