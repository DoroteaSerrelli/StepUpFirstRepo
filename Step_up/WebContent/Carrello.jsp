<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ItemCarrello, model.Carrello, controller.ManageCarrello, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<script src = "scripts/CostoTotale.jsp"></script>
<%@include file="Header.jsp"%>
</head>
<body>

	<div id="page">
		<h3>Il tuo carrello</h3>
		<p>
			<% Carrello cart = ((Carrello)request.getSession().getAttribute("Carrello"));  
			if (cart == null){%>
				Numero di articoli: 0
			<%}else{ %>
			Numero di articoli: <%= cart.getNumProdotti() %>
			<%} %>
			</p>
		<div id="container">
			<div id="Products"></div>
			<table>
				<tr>
					<th></th>
					<th>Nome</th>
					<th>Descrizione breve</th>
					<th>Quantità</th>
					<th>Prezzo</th>
					<th>Cancella</th>
				</tr>
				<% 	if(cart != null){
					List<ItemCarrello> items = cart.getProducts();
					for(ItemCarrello i : items){%>
						<tr>
							<td><%= i.getNomeProdotto() %></td>
							<td><%= i.getDescrizione_breve() %></td>
							<td> <%= i.getQuantità() %></td>
							<td><%= i.getPrezzo()*i.getQuantità() %></td>
							<td><a href = "ManageCarrello?action=delete&codice=<%=i.getIDProdotto()%>">Cancella</a></td>
						
						</tr>
					<%}}
				%>

				<tr>

				</tr>
			</table>


		<a href = "common/Ordine.jsp"><button type = "pulsante" name = "CheckOut">Acquista i prodotti</button></a>
		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>