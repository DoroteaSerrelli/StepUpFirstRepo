<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "model.OrdineDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagamento</title>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<script src = "<%= request.getContextPath()%>/scripts/commonscripts.js"></script>
</head>
<body>
<%@include file = "../Header.jsp" %>
	<%OrdineDTO pratica = (OrdineDTO) request.getAttribute("PraticaOrdine");%>
	<div id="PagamentoField">
		<form name="Pagamento" action="ManagePagamento" method="POST">
		<input type = "hidden" name = "IDOrdine" value = "<%= pratica.getIDOrdine()%>">
		<fieldset>
			<legend>Dettagli sulla spedizione</legend>
			<label for = "MetSpedizione">Metodo di spedizione</label>
			<select name = "MetSpedizione">
				<option value = "Spedizione standard">Spedizione standard (costo 5 euro)</option>
				<option value = "Spedizione assicurata">Spedizione assicurata (costo 8 euro)</option>
				<option value = "Spedizione premium">Spedizione premium (costo 10 euro)</option>
			</select>
			<label for = "MetConsegna">Metodo di consegna</label>
			<select name = "MetConsegna">
				<option value = "Consegna a domicilio">Consegna a domicilio</option>
				<option value = "Consegna presso punto di ritiro">Consegna presso un punto di ritiro</option>
				<option value = "Consegna priority">Consegna priority</option>
			</select>
		</fieldset>
			<fieldset>
				<legend>Metodo di pagamento</legend>
				<input type="radio" name="MetPagamento" value="Carta di credito"
					onclick="showdatiPagamento()" />Carta di credito<br> <input
					type="radio" name="MetPagamento" value="Carta prepagata"
					onclick="showdatiPagamento()" />Carta prepagata<br> <input
					type="radio" name="MetPagamento" value="Contanti alla consegna"
					onclick="showdatiPagamento()" />Contanti alla consegna<br> <input
					type="radio" name="MetPagamento" value="Paypal"
					onclick="showdatiPagamento()" />Paypal<br>
					<input type = "hidden" name = "errorRadioB"/>
					<span id = "errorMetPagamento"></span>
					<p>Pagamenti accettati<img src = "<%= request.getContextPath()%>/images/carta-di-credito-paypal.jpg" width = "70" height = "50"></p>
				<hr>
				<div id="datiPagamento" style="display: none"></div>
			</fieldset>

		</form>
	</div>

<%@include file = "../Footer.jsp" %>
</body>
</html>