<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.ItemCarrello, model.Carrello, java.util.List, dao.PhotoControl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
<script src = "<%=request.getContextPath() %>/scripts/Cart.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div id="page">
		<h3>Il tuo carrello</h3>
		<p>
			<% double costo = 0.00; 
			Carrello cart = ((Carrello)request.getSession().getAttribute("Carrello"));  
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
					<th>Prezzo unitario</th>
					<th>Quantità</th>
					<th>Prezzo per quantità</th>
					<th>Cancella</th>
				</tr>
				<%
				if (cart != null) {
					List<ItemCarrello> items = cart.getProducts();
					for (ItemCarrello i : items) {
				%>
				<tr>
					<td><img class="TopProduct"
						src="GetProductTopImage?Codice=<%=i.getIDProdotto()%>"
						onerror="this.src='<%=request.getContextPath()%>/images/NoPhotoAvailable.jpg'"
						style="width: 100px; height: 100px" alt="Product_image" /></td>
					<td><%=i.getNomeProdotto()%></td>
					<td><%=i.getPrezzo()%></td>
					<td><input type="number" min="1" max="10" id = "<%=i.getIDProdotto() %>"
						value="<%=i.getQuantità()%>" onchange="changeQuantity(this)"></td>
					<td><%costo += Math.round(i.getPrezzo() * i.getQuantità()*100.00)/100.00;%><%= Math.round(i.getPrezzo() * i.getQuantità() * 100.00)/100.00%></td>
					<td><a
						href="ManageCarrello?action=remove&codice=<%=i.getIDProdotto()%>">Cancella</a></td>

				</tr>
				<%
				}
				}
				%>

				<tr>

				</tr>
			</table>
		<p>Costo totale: <%= Math.round(costo * 100.0) / 100.0 %></p>
		<a href = "ManageCarrello?action=delete"><button class = "pulsante" name = "SvuotaCarrello">Svuota il carrello</button></a>
		<%if(cart.getNumProdotti() > 0){ %>
			<a href = "common/Ordine.jsp"><button class = "pulsante" name = "CheckOut">Acquista i prodotti</button></a>
			<%} %>
		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>