<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "model.ItemCarrello, model.Carrello, java.util.List, dao.PhotoControl"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width:device-width, initial-scale = 1.0">
<meta charset="UTF-8">
<title>Carrello</title>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
<script src = "<%=request.getContextPath() %>/scripts/Cart.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div id="page">
		<h3>Il tuo carrello</h3>
		<p>
			<% 
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
					<td> <button type = "button" onclick = "changePlusQuantity(<%=i.getIDProdotto()%>)">+</button>
					<input type="text" id = "<%=i.getIDProdotto() %>" value="<%=i.getQuantità()%>">
						<button type = "button" onclick = "changeMinusQuantity(<%=i.getIDProdotto()%>)">-</button>
						</td>
					<td><%= Math.round(i.getPrezzo() * i.getQuantità() * 100.00)/100.00%></td>
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
		<p>Costo totale: <%if(cart == null){%> 0.00<%}else{ %> <%= cart.totaleSpesa() %><%} %></p>
		<a href = "ManageCarrello?action=delete"><button class = "pulsante" name = "SvuotaCarrello">Svuota il carrello</button></a>
		<%if(cart != null && cart.getNumProdotti() > 0){ %>
			<a href = "common/Ordine.jsp"><button class = "pulsante" name = "CheckOut">Acquista i prodotti</button></a>
			<%} %>
		</div>
	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>